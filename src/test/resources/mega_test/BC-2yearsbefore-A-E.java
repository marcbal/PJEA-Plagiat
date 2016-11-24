package net.md_5.bungee.protocol;

import net.md_5.bungee.protocol.packet.KeepAlive;
import net.md_5.bungee.protocol.packet.ClientSettings;
import net.md_5.bungee.protocol.packet.ClientStatus;
import net.md_5.bungee.protocol.packet.Login;
import net.md_5.bungee.protocol.packet.Chat;
import net.md_5.bungee.protocol.packet.EncryptionRequest;
import net.md_5.bungee.protocol.packet.PlayerListHeaderFooter;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import net.md_5.bungee.protocol.packet.SetCompression;
import net.md_5.bungee.protocol.packet.TabCompleteRequest;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardScore;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import net.md_5.bungee.protocol.packet.Team;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.protocol.packet.Respawn;
import net.md_5.bungee.protocol.packet.Handshake;
import net.md_5.bungee.protocol.packet.EncryptionResponse;
import net.md_5.bungee.protocol.packet.LegacyHandshake;
import net.md_5.bungee.protocol.packet.LegacyPing;
import net.md_5.bungee.protocol.packet.LoginRequest;
import net.md_5.bungee.protocol.packet.LoginSuccess;
import net.md_5.bungee.protocol.packet.PingPacket;
import net.md_5.bungee.protocol.packet.StatusRequest;
import net.md_5.bungee.protocol.packet.StatusResponse;
import net.md_5.bungee.protocol.packet.TabCompleteResponse;
import net.md_5.bungee.protocol.packet.Title;
import com.google.common.base.Preconditions;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;
import com.google.common.base.Preconditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import java.util.Arrays;
import java.util.HashSet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.GsonBuilder;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.module.ModuleManager;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.log.BungeeLogger;
import net.md_5.bungee.scheduler.BungeeScheduler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.util.ResourceLeakDetector;
import net.md_5.bungee.conf.Configuration;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import jline.UnsupportedTerminal;
import jline.console.ConsoleReader;
import jline.internal.Log;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ReconnectHandler;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.command.*;
import net.md_5.bungee.conf.YamlConfig;
import net.md_5.bungee.forge.ForgeConstants;
import net.md_5.bungee.log.LoggingOutputStream;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.Chat;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.query.RemoteQuery;
import net.md_5.bungee.util.CaseInsensitiveMap;
import org.fusesource.jansi.AnsiConsole;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.command.ConsoleCommandSender;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import net.md_5.bungee.BungeeCord;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import gnu.trove.TCollections;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.api.scheduler.TaskScheduler;
import java.security.AccessControlException;
import java.security.Permission;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import com.google.common.base.Preconditions;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.ToString;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.connection.PingHandler;
import net.md_5.bungee.netty.HandlerBoss;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import lombok.Data;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.Title.Action;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import gnu.trove.strategy.HashingStrategy;
import gnu.trove.map.hash.TCustomHashMap;
import java.util.Map;
import gnu.trove.set.hash.TCustomHashSet;
import java.util.Collection;
import org.junit.Test;
import org.junit.Assert;
import net.md_5.bungee.protocol.PacketCompressor;
import net.md_5.bungee.protocol.PacketDecompressor;
import net.md_5.bungee.protocol.PacketWrapper;
import com.google.common.base.Preconditions;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import net.md_5.bungee.protocol.MinecraftDecoder;
import net.md_5.bungee.protocol.MinecraftEncoder;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.plugin.Cancellable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCipher;
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCipher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import jline.console.ConsoleReader;
import net.md_5.bungee.api.ChatColor;
import org.fusesource.jansi.Ansi;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import com.google.common.base.Joiner;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.command.PlayerCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import java.util.HashSet;
import java.util.Set;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.event.ProxyReloadEvent;
import com.google.common.collect.ImmutableSet;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.md_5.bungee.api.chat.BaseComponent;
import java.util.Collection;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.Map;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import java.lang.reflect.Type;
import java.util.HashSet;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.junit.Assert;
import org.junit.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import com.google.common.base.Preconditions;
import gnu.trove.map.TMap;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import lombok.Getter;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.util.CaseInsensitiveMap;
import net.md_5.bungee.util.CaseInsensitiveSet;
import java.util.Collection;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.net.InetSocketAddress;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.protocol.DefinedPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.io.DataInput;
import java.util.Objects;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.ServerConnection;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.score.Objective;
import net.md_5.bungee.api.score.Position;
import net.md_5.bungee.api.score.Score;
import net.md_5.bungee.api.score.Scoreboard;
import net.md_5.bungee.api.score.Team;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.PacketWrapper;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.KeepAlive;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardScore;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.protocol.packet.SetCompression;
import net.md_5.bungee.tab.TabList;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import net.md_5.bungee.protocol.packet.EncryptionResponse;
import net.md_5.bungee.protocol.packet.EncryptionRequest;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.connection.LoginResult;
import net.md_5.bungee.protocol.DefinedPacket;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.CountDownLatch;
import org.junit.Assert;
import org.junit.Test;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.concurrent.CountDownLatch;
import org.junit.Assert;
import org.junit.Test;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import java.security.GeneralSecurityException;
import com.google.common.io.BaseEncoding;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.ArrayDeque;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.Map;
import net.md_5.bungee.ServerConnector;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.forge.ForgeLogger.LogDirection;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.regex.Pattern;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.logging.Level;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.ArrayDeque;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.forge.ForgeLogger.LogDirection;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.forge.ForgeLogger.LogDirection;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.packet.PluginMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.connection.LoginResult;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import java.util.Collection;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import java.util.concurrent.ThreadFactory;
import lombok.Data;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.PacketWrapper;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import java.io.IOException;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.connection.CancelSendSignal;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.connection.PingHandler;
import net.md_5.bungee.protocol.BadPacketException;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.netty.PipelineUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.Callback;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLEngine;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.packet.PluginMessage;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import javax.crypto.SecretKey;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.*;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.http.HttpClient;
import net.md_5.bungee.netty.HandlerBoss;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PacketHandler;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.netty.cipher.CipherDecoder;
import net.md_5.bungee.netty.cipher.CipherEncoder;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.packet.Handshake;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.EncryptionResponse;
import net.md_5.bungee.protocol.packet.EncryptionRequest;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.LegacyHandshake;
import net.md_5.bungee.protocol.packet.LegacyPing;
import net.md_5.bungee.protocol.packet.LoginRequest;
import net.md_5.bungee.protocol.packet.LoginSuccess;
import net.md_5.bungee.protocol.packet.PingPacket;
import net.md_5.bungee.protocol.packet.StatusRequest;
import net.md_5.bungee.protocol.packet.StatusResponse;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import lombok.Data;
import net.md_5.bungee.Util;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import net.md_5.bungee.protocol.packet.LegacyHandshake;
import net.md_5.bungee.protocol.packet.LegacyPing;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import java.net.InetSocketAddress;
import java.util.Map;
import lombok.Data;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.LogRecord;
import com.google.common.base.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Cancellable;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.TimerTask;
import net.md_5.bungee.api.ProxyServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Setter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import lombok.Setter;
import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.charset.Charset;
import java.util.Arrays;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.util.CaseInsensitiveMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import lombok.Data;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.Setter;
import java.util.zip.Deflater;
import net.md_5.bungee.protocol.packet.ClientStatus;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Respawn;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import java.util.zip.Inflater;
import net.md_5.bungee.protocol.PacketWrapper;
import io.netty.buffer.ByteBuf;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.net.InetSocketAddress;
import java.util.UUID;
import net.md_5.bungee.api.config.ListenerInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Event;
import com.google.gson.Gson;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PacketHandler;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.MinecraftDecoder;
import net.md_5.bungee.protocol.MinecraftEncoder;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.Handshake;
import net.md_5.bungee.protocol.packet.StatusRequest;
import net.md_5.bungee.protocol.packet.StatusResponse;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.AttributeKey;
import io.netty.util.internal.PlatformDependent;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.BungeeServerInfo;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.Util;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.protocol.KickStringWriter;
import net.md_5.bungee.protocol.LegacyDecoder;
import net.md_5.bungee.protocol.MinecraftDecoder;
import net.md_5.bungee.protocol.MinecraftEncoder;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.Varint21FrameDecoder;
import net.md_5.bungee.protocol.Varint21LengthFieldPrepender;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.protocol.packet.Handshake;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.md_5.bungee.api.ServerPing;
import java.lang.reflect.Type;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import java.util.UUID;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import net.md_5.bungee.api.plugin.Plugin;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.Plugin;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.Subscribe;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.event.EventBus;
import net.md_5.bungee.event.EventHandler;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.MinecraftInput;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Cancellable;
import com.google.common.base.Preconditions;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.protocol.packet.Chat;
import net.md_5.bungee.protocol.packet.ClientSettings;
import net.md_5.bungee.protocol.packet.EncryptionRequest;
import net.md_5.bungee.protocol.packet.EncryptionResponse;
import net.md_5.bungee.protocol.packet.Handshake;
import net.md_5.bungee.protocol.packet.KeepAlive;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.protocol.packet.Login;
import net.md_5.bungee.protocol.packet.LoginRequest;
import net.md_5.bungee.protocol.packet.LoginSuccess;
import net.md_5.bungee.protocol.packet.PingPacket;
import net.md_5.bungee.protocol.packet.PlayerListHeaderFooter;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Respawn;
import net.md_5.bungee.protocol.packet.ScoreboardDisplay;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardScore;
import net.md_5.bungee.protocol.packet.SetCompression;
import net.md_5.bungee.protocol.packet.StatusRequest;
import net.md_5.bungee.protocol.packet.StatusResponse;
import net.md_5.bungee.protocol.packet.TabCompleteRequest;
import net.md_5.bungee.protocol.packet.TabCompleteResponse;
import net.md_5.bungee.protocol.packet.Team;
import net.md_5.bungee.protocol.packet.Title;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import java.util.Collection;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.PluginManager;
import com.google.common.base.Preconditions;
import java.io.File;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import lombok.Getter;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import java.net.InetSocketAddress;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.api.plugin.Plugin;
import lombok.Data;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.api.config.ServerInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Kick;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.score.Objective;
import net.md_5.bungee.api.score.Scoreboard;
import net.md_5.bungee.api.score.Team;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.connection.CancelSendSignal;
import net.md_5.bungee.connection.DownstreamBridge;
import net.md_5.bungee.connection.LoginResult;
import net.md_5.bungee.forge.ForgeConstants;
import net.md_5.bungee.forge.ForgeServerHandler;
import net.md_5.bungee.forge.ForgeUtils;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.HandlerBoss;
import net.md_5.bungee.netty.PacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.MinecraftOutput;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.EncryptionRequest;
import net.md_5.bungee.protocol.packet.Handshake;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.protocol.packet.Login;
import net.md_5.bungee.protocol.packet.LoginSuccess;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.Respawn;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.SetCompression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import java.net.InetSocketAddress;
import java.util.Collection;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.md_5.bungee.Util;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.api.CommandSender;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.connection.LoginResult;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.plugin.Event;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.command.ConsoleCommandSender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import java.lang.reflect.Type;
import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Assert;
import org.junit.Test;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.ToString;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import com.google.common.base.Preconditions;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.PacketHandler;
import net.md_5.bungee.protocol.PacketWrapper;
import net.md_5.bungee.protocol.packet.KeepAlive;
import net.md_5.bungee.protocol.packet.Chat;
import net.md_5.bungee.protocol.packet.PlayerListItem;
import net.md_5.bungee.protocol.packet.TabCompleteRequest;
import net.md_5.bungee.protocol.packet.ClientSettings;
import net.md_5.bungee.protocol.packet.PluginMessage;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.forge.ForgeConstants;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.TabCompleteResponse;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.util.internal.PlatformDependent;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PermissionCheckEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.score.Scoreboard;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.entitymap.EntityMap;
import net.md_5.bungee.forge.ForgeClientHandler;
import net.md_5.bungee.forge.ForgeServerHandler;
import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.netty.HandlerBoss;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.MinecraftDecoder;
import net.md_5.bungee.protocol.MinecraftEncoder;
import net.md_5.bungee.protocol.PacketWrapper;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;
import net.md_5.bungee.protocol.packet.Chat;
import net.md_5.bungee.protocol.packet.ClientSettings;
import net.md_5.bungee.protocol.packet.Kick;
import net.md_5.bungee.protocol.packet.PlayerListHeaderFooter;
import net.md_5.bungee.protocol.packet.PluginMessage;
import net.md_5.bungee.protocol.packet.SetCompression;
import net.md_5.bungee.tab.ServerUnique;
import net.md_5.bungee.tab.TabList;
import net.md_5.bungee.util.CaseInsensitiveSet;
import com.google.common.base.Joiner;
import java.net.InetSocketAddress;
import java.util.UUID;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.channel.ChannelHandler;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.util.CaseInsensitiveMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import net.md_5.bungee.api.AbstractReconnectHandler;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.util.CaseInsensitiveMap;
import org.yaml.snakeyaml.Yaml;

abstract class AbstractPacketHandler {
    public void handle(LegacyPing ping) throws Exception {
        }
    public void handle(TabCompleteResponse tabResponse) throws Exception {
        }
    public void handle(PingPacket ping) throws Exception {
        }
    public void handle(StatusRequest statusRequest) throws Exception {
        }
    public void handle(StatusResponse statusResponse) throws Exception {
        }
    public void handle(Handshake handshake) throws Exception {
        }
    public void handle(KeepAlive keepAlive) throws Exception {
        }
    public void handle(Login login) throws Exception {
        }
    public void handle(Chat chat) throws Exception {
        }
    public void handle(Respawn respawn) throws Exception {
        }
    public void handle(LoginRequest loginRequest) throws Exception {
        }
    public void handle(ClientSettings settings) throws Exception {
        }
    public void handle(ClientStatus clientStatus) throws Exception {
        }
    public void handle(PlayerListItem playerListItem) throws Exception {
        }
    public void handle(PlayerListHeaderFooter playerListHeaderFooter) throws Exception {
        }
    public void handle(TabCompleteRequest tabComplete) throws Exception {
        }
    public void handle(ScoreboardObjective scoreboardObjective) throws Exception {
        }
    public void handle(ScoreboardScore scoreboardScore) throws Exception {
        }
    public void handle(EncryptionRequest encryptionRequest) throws Exception {
        }
    public void handle(ScoreboardDisplay displayScoreboard) throws Exception {
        }
    public void handle(Team team) throws Exception {
        }
    public void handle(Title title) throws Exception {
        }
    public void handle(PluginMessage pluginMessage) throws Exception {
        }
    public void handle(Kick kick) throws Exception {
        }
    public void handle(EncryptionResponse encryptionResponse) throws Exception {
        }
    public void handle(LoginSuccess loginSuccess) throws Exception {
        }
    public void handle(LegacyHandshake legacyHandshake) throws Exception {
        }
    public void handle(SetCompression setCompression) throws Exception {
        }
    }
abstract class AbstractReconnectHandler implements ReconnectHandler {
    @Override public ServerInfo getServer(ProxiedPlayer player) {
        ServerInfo server = getForcedHost(player.getPendingConnection());
        if (server == null) {
            server = getStoredServer(player);
            if (server == null) {
                server = ProxyServer.getInstance().getServerInfo(player.getPendingConnection().getListener().getDefaultServer());
                }
            Preconditions.checkState(server != null, "Default server not defined");
            }
        return server;
        }
    public static ServerInfo getForcedHost(PendingConnection con) {
        if (con.getVirtualHost() == null) {
            return null;
            }
        String forced = con.getListener().getForcedHosts().get(con.getVirtualHost().getHostString());
        if (forced == null && con.getListener().isForceDefault()) {
            forced = con.getListener().getDefaultServer();
            }
        return ProxyServer.getInstance().getServerInfo(forced);
        }
    protected abstract ServerInfo getStoredServer(ProxiedPlayer player);
    }
@Data @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) class AsyncEvent < T > extends Event {
    private final Callback < T > done;
    private final Set < Plugin > intents = Collections.newSetFromMap(new ConcurrentHashMap < Plugin, Boolean > ());
    private final AtomicBoolean fired = new AtomicBoolean();
    private final AtomicInteger latch = new AtomicInteger();
    @Override @SuppressWarnings("unchecked") public void postCall() {
        fired.set(true);
        if (latch.get() == 0) {
            done.done((T) this, null);
            }
        }
    public void registerIntent(Plugin plugin) {
        Preconditions.checkState(! fired.get(), "Event %s has already been fired", this);
        Preconditions.checkState(! intents.contains(plugin), "Plugin %s already registered intent for event %s", plugin, this);
        intents.add(plugin);
        latch.incrementAndGet();
        }
    @SuppressWarnings("unchecked") public void completeIntent(Plugin plugin) {
        Preconditions.checkState(intents.contains(plugin), "Plugin %s has not registered intent for event %s", plugin, this);
        intents.remove(plugin);
        if (latch.decrementAndGet() == 0 && fired.get()) {
            done.done((T) this, null);
            }
        }
    }
class BadPacketException extends RuntimeException {
    public BadPacketException(String message) {
        super (message);
        }
    public BadPacketException(String message, Throwable cause) {
        super (message, cause);
        }
    }
@Setter @ToString @NoArgsConstructor abstract class BaseComponent {
    @Setter(AccessLevel.NONE) BaseComponent parent;
    private ChatColor color;
    private Boolean bold;
    private Boolean italic;
    private Boolean underlined;
    private Boolean strikethrough;
    private Boolean obfuscated;
    @Getter private List < BaseComponent > extra;
    @Getter private ClickEvent clickEvent;
    @Getter private HoverEvent hoverEvent;
    BaseComponent(BaseComponent old) {
        setColor(old.getColorRaw());
        setBold(old.isBoldRaw());
        setItalic(old.isItalicRaw());
        setUnderlined(old.isUnderlinedRaw());
        setStrikethrough(old.isStrikethroughRaw());
        setObfuscated(old.isObfuscatedRaw());
        setClickEvent(old.getClickEvent());
        setHoverEvent(old.getHoverEvent());
        if (extra != null) {
            for (BaseComponent component : extra) {
                addExtra(component.duplicate());
                }
            }
        }
    public abstract BaseComponent duplicate();
    public static String toLegacyText(BaseComponent ... components) {
        StringBuilder builder = new StringBuilder();
        for (BaseComponent msg : components) {
            builder.append(msg.toLegacyText());
            }
        return builder.toString();
        }
    public static String toPlainText(BaseComponent ... components) {
        StringBuilder builder = new StringBuilder();
        for (BaseComponent msg : components) {
            builder.append(msg.toPlainText());
            }
        return builder.toString();
        }
    public ChatColor getColor() {
        if (color == null) {
            if (parent == null) {
                return ChatColor.WHITE;
                }
            return parent.getColor();
            }
        return color;
        }
    public ChatColor getColorRaw() {
        return color;
        }
    public boolean isBold() {
        if (bold == null) {
            return parent != null && parent.isBold();
            }
        return bold;
        }
    public Boolean isBoldRaw() {
        return bold;
        }
    public boolean isItalic() {
        if (italic == null) {
            return parent != null && parent.isItalic();
            }
        return italic;
        }
    public Boolean isItalicRaw() {
        return italic;
        }
    public boolean isUnderlined() {
        if (underlined == null) {
            return parent != null && parent.isUnderlined();
            }
        return underlined;
        }
    public Boolean isUnderlinedRaw() {
        return underlined;
        }
    public boolean isStrikethrough() {
        if (strikethrough == null) {
            return parent != null && parent.isStrikethrough();
            }
        return strikethrough;
        }
    public Boolean isStrikethroughRaw() {
        return strikethrough;
        }
    public boolean isObfuscated() {
        if (obfuscated == null) {
            return parent != null && parent.isObfuscated();
            }
        return obfuscated;
        }
    public Boolean isObfuscatedRaw() {
        return obfuscated;
        }
    public void setExtra(List < BaseComponent > components) {
        for (BaseComponent component : components) {
            component.parent = this;
            }
        extra = components;
        }
    public void addExtra(String text) {
        addExtra(new TextComponent(text));
        }
    public void addExtra(BaseComponent component) {
        if (extra == null) {
            extra = new ArrayList < > ();
            }
        component.parent = this;
        extra.add(component);
        }
    public boolean hasFormatting() {
        return color != null || bold != null || italic != null || underlined != null || strikethrough != null || obfuscated != null || hoverEvent != null || clickEvent != null;
        }
    public String toPlainText() {
        StringBuilder builder = new StringBuilder();
        toPlainText(builder);
        return builder.toString();
        }
    void toPlainText(StringBuilder builder) {
        if (extra != null) {
            for (BaseComponent e : extra) {
                e.toPlainText(builder);
                }
            }
        }
    public String toLegacyText() {
        StringBuilder builder = new StringBuilder();
        toLegacyText(builder);
        return builder.toString();
        }
    void toLegacyText(StringBuilder builder) {
        if (extra != null) {
            for (BaseComponent e : extra) {
                e.toLegacyText(builder);
                }
            }
        }
    }
class BaseComponentSerializer {
    protected void deserialize(JsonObject object, BaseComponent component, JsonDeserializationContext context) {
        if (object.has("color")) {
            component.setColor(ChatColor.valueOf(object.get("color").getAsString().toUpperCase()));
            }
        if (object.has("bold")) {
            component.setBold(object.get("bold").getAsBoolean());
            }
        if (object.has("italic")) {
            component.setItalic(object.get("italic").getAsBoolean());
            }
        if (object.has("underlined")) {
            component.setUnderlined(object.get("underlined").getAsBoolean());
            }
        if (object.has("strikethrough")) {
            component.setUnderlined(object.get("strikethrough").getAsBoolean());
            }
        if (object.has("obfuscated")) {
            component.setUnderlined(object.get("obfuscated").getAsBoolean());
            }
        if (object.has("extra")) {
            component.setExtra(Arrays.< BaseComponent > asList(context.< BaseComponent[] > deserialize(object.get("extra"), BaseComponent[].class)));
            }
        if (object.has("clickEvent")) {
            JsonObject event = object.getAsJsonObject("clickEvent");
            component.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(event.get("action").getAsString().toUpperCase()), event.get("value").getAsString()));
            }
        if (object.has("hoverEvent")) {
            JsonObject event = object.getAsJsonObject("hoverEvent");
            BaseComponent[] res;
            if (event.get("value").isJsonArray()) {
                res = context.deserialize(event.get("value"), BaseComponent[].class);
                }
            else {
                res = new BaseComponent[] {
                    context.< BaseComponent > deserialize(event.get("value"), BaseComponent.class) }
                ;
                }
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.valueOf(event.get("action").getAsString().toUpperCase()), res));
            }
        }
    protected void serialize(JsonObject object, BaseComponent component, JsonSerializationContext context) {
        boolean first = false;
        if (ComponentSerializer.serializedComponents.get() == null) {
            first = true;
            ComponentSerializer.serializedComponents.set(new HashSet < BaseComponent > ());
            }
        try {
            Preconditions.checkArgument(! ComponentSerializer.serializedComponents.get().contains(component), "Component loop");
            ComponentSerializer.serializedComponents.get().add(component);
            if (component.getColorRaw() != null) {
                object.addProperty("color", component.getColorRaw().getName());
                }
            if (component.isBoldRaw() != null) {
                object.addProperty("bold", component.isBoldRaw());
                }
            if (component.isItalicRaw() != null) {
                object.addProperty("italic", component.isItalicRaw());
                }
            if (component.isUnderlinedRaw() != null) {
                object.addProperty("underlined", component.isUnderlinedRaw());
                }
            if (component.isStrikethroughRaw() != null) {
                object.addProperty("strikethrough", component.isStrikethroughRaw());
                }
            if (component.isObfuscatedRaw() != null) {
                object.addProperty("obfuscated", component.isObfuscatedRaw());
                }
            if (component.getExtra() != null) {
                object.add("extra", context.serialize(component.getExtra()));
                }
            if (component.getClickEvent() != null) {
                JsonObject clickEvent = new JsonObject();
                clickEvent.addProperty("action", component.getClickEvent().getAction().toString().toLowerCase());
                clickEvent.addProperty("value", component.getClickEvent().getValue());
                object.add("clickEvent", clickEvent);
                }
            if (component.getHoverEvent() != null) {
                JsonObject hoverEvent = new JsonObject();
                hoverEvent.addProperty("action", component.getHoverEvent().getAction().toString().toLowerCase());
                hoverEvent.add("value", context.serialize(component.getHoverEvent().getValue()));
                object.add("hoverEvent", hoverEvent);
                }
            }
        finally {
            ComponentSerializer.serializedComponents.get().remove(component);
            if (first) {
                ComponentSerializer.serializedComponents.set(null);
                }
            }
        }
    }
class Bootstrap {
    public static void main(String[] args) throws Exception {
        if (Float.parseFloat(System.getProperty("java.class.version")) < 51.0) {
            System.err.println("*** ERROR *** BungeeCord requires Java 7 or above to function! Please download and install it!");
            System.out.println("You can check your Java version with the command: java -version");
            return;
            }
        BungeeCordLauncher.main(args);
        }
    }
interface BungeeCipher {
    void init(boolean forEncryption, SecretKey key) throws GeneralSecurityException;
    void free();
    void cipher(ByteBuf in, ByteBuf out) throws GeneralSecurityException;
    ByteBuf cipher(ChannelHandlerContext ctx, ByteBuf in) throws GeneralSecurityException;
    }
class BungeeCord extends ProxyServer {
    public volatile boolean isRunning;
    @Getter public final Configuration config = new Configuration();
    public ResourceBundle bundle;
    public EventLoopGroup eventLoops;
    private final Timer saveThread = new Timer("Reconnect Saver");
    private final Timer metricsThread = new Timer("Metrics Thread");
    private final Collection < Channel > listeners = new HashSet < > ();
    private final Map < String, UserConnection > connections = new CaseInsensitiveMap < > ();
    private final Map < UUID, UserConnection > connectionsByOfflineUUID = new HashMap < > ();
    private final ReadWriteLock connectionLock = new ReentrantReadWriteLock();
    @Getter public final PluginManager pluginManager = new PluginManager(this);
    @Getter @Setter private ReconnectHandler reconnectHandler;
    @Getter @Setter private ConfigurationAdapter configurationAdapter = new YamlConfig();
    private final Collection < String > pluginChannels = new HashSet < > ();
    @Getter private final File pluginsFolder = new File("plugins");
    @Getter private final BungeeScheduler scheduler = new BungeeScheduler();
    @Getter private final ConsoleReader consoleReader;
    @Getter private final Logger logger;
    public final Gson gson = new GsonBuilder().registerTypeAdapter(ServerPing.PlayerInfo.class, new PlayerInfoSerializer(ProtocolConstants.MINECRAFT_1_7_6)).registerTypeAdapter(Favicon.class, Favicon.getFaviconTypeAdapter()).create();
    public final Gson gsonLegacy = new GsonBuilder().registerTypeAdapter(ServerPing.PlayerInfo.class, new PlayerInfoSerializer(ProtocolConstants.MINECRAFT_1_7_2)).registerTypeAdapter(Favicon.class, Favicon.getFaviconTypeAdapter()).create();
    @Getter private ConnectionThrottle connectionThrottle;
    private final ModuleManager moduleManager = new ModuleManager();
    {
        getPluginManager().registerCommand(null, new CommandReload());
        getPluginManager().registerCommand(null, new CommandEnd());
        getPluginManager().registerCommand(null, new CommandIP());
        getPluginManager().registerCommand(null, new CommandBungee());
        getPluginManager().registerCommand(null, new CommandPerms());
        registerChannel("BungeeCord");
        }
    public static BungeeCord getInstance() {
        return (BungeeCord) ProxyServer.getInstance();
        }
    @SuppressFBWarnings("DM_DEFAULT_ENCODING") public BungeeCord() throws IOException {
        Preconditions.checkState(new File(".").getAbsolutePath().indexOf('!') == - 1, "Cannot use BungeeCord in directory with ! in path.");
        System.setSecurityManager(new BungeeSecurityManager());
        try {
            bundle = ResourceBundle.getBundle("messages");
            }
        catch (MissingResourceException ex) {
            bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
            }
        Log.setOutput(new PrintStream(ByteStreams.nullOutputStream()));
        AnsiConsole.systemInstall();
        consoleReader = new ConsoleReader();
        consoleReader.setExpandEvents(false);
        logger = new BungeeLogger(this);
        System.setErr(new PrintStream(new LoggingOutputStream(logger, Level.SEVERE), true));
        System.setOut(new PrintStream(new LoggingOutputStream(logger, Level.INFO), true));
        if (consoleReader.getTerminal() instanceof UnsupportedTerminal) {
            logger.info("Unable to initialize fancy terminal. To fix this on Windows, install the correct Microsoft Visual C++ 2008 Runtime");
            logger.info("NOTE: This error is non crucial, and BungeeCord will still function correctly! Do not bug the author about it unless you are still unable to get it working");
            }
        if (NativeCipher.load()) {
            logger.info("Using OpenSSL based native cipher.");
            }
        else {
            logger.info("Using standard Java JCE cipher. To enable the OpenSSL based native cipher, please make sure you are using 64 bit Ubuntu or Debian with libssl installed.");
            }
        }
    @Override @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE") public void start() throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("io.netty.selectorAutoRebuildThreshold", "0");
        ResourceLeakDetector.setEnabled(false);
        eventLoops = PipelineUtils.newEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty IO Thread #%1$d").build());
        File moduleDirectory = new File("modules");
        moduleManager.load(this, moduleDirectory);
        pluginManager.detectPlugins(moduleDirectory);
        pluginsFolder.mkdir();
        pluginManager.detectPlugins(pluginsFolder);
        pluginManager.loadPlugins();
        config.load();
        registerChannel(ForgeConstants.FML_TAG);
        registerChannel(ForgeConstants.FML_HANDSHAKE_TAG);
        registerChannel(ForgeConstants.FORGE_REGISTER);
        isRunning = true;
        pluginManager.enablePlugins();
        connectionThrottle = new ConnectionThrottle(config.getThrottle());
        startListeners();
        saveThread.scheduleAtFixedRate(new TimerTask() {
            @Override public void run() {
                if (getReconnectHandler() != null) {
                    getReconnectHandler().save();
                    }
                }
            }
        , 0, TimeUnit.MINUTES.toMillis(5));
        metricsThread.scheduleAtFixedRate(new Metrics(), 0, TimeUnit.MINUTES.toMillis(Metrics.PING_INTERVAL));
        }
    public void startListeners() {
        for (final ListenerInfo info : config.getListeners()) {
            ChannelFutureListener listener = new ChannelFutureListener() {
                @Override public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        listeners.add(future.channel());
                        getLogger().log(Level.INFO, "Listening on {0}", info.getHost());
                        }
                    else {
                        getLogger().log(Level.WARNING, "Could not bind to host " + info.getHost(), future.cause());
                        }
                    }
                }
            ;
            new ServerBootstrap().channel(PipelineUtils.getServerChannel()).option(ChannelOption.SO_REUSEADDR, true).childAttr(PipelineUtils.LISTENER, info).childHandler(PipelineUtils.SERVER_CHILD).group(eventLoops).localAddress(info.getHost()).bind().addListener(listener);
            if (info.isQueryEnabled()) {
                ChannelFutureListener bindListener = new ChannelFutureListener() {
                    @Override public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            listeners.add(future.channel());
                            getLogger().log(Level.INFO, "Started query on {0}", future.channel().localAddress());
                            }
                        else {
                            getLogger().log(Level.WARNING, "Could not bind to host " + info.getHost(), future.cause());
                            }
                        }
                    }
                ;
                new RemoteQuery(this, info).start(PipelineUtils.getDatagramChannel(), new InetSocketAddress(info.getHost().getAddress(), info.getQueryPort()), eventLoops, bindListener);
                }
            }
        }
    public void stopListeners() {
        for (Channel listener : listeners) {
            getLogger().log(Level.INFO, "Closing listener {0}", listener);
            try {
                listener.close().syncUninterruptibly();
                }
            catch (ChannelException ex) {
                getLogger().severe("Could not close listen thread");
                }
            }
        listeners.clear();
        }
    @Override public void stop() {
        new Thread("Shutdown Thread") {
            @Override @SuppressFBWarnings("DM_EXIT") @SuppressWarnings("TooBroadCatch") public void run() {
                BungeeCord.this.isRunning = false;
                stopListeners();
                getLogger().info("Closing pending connections");
                connectionLock.readLock().lock();
                try {
                    getLogger().log(Level.INFO, "Disconnecting {0} connections", connections.size());
                    for (UserConnection user : connections.values()) {
                        user.disconnect(getTranslation("restart"));
                        }
                    }
                finally {
                    connectionLock.readLock().unlock();
                    }
                getLogger().info("Closing IO threads");
                eventLoops.shutdownGracefully();
                try {
                    eventLoops.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                    }
                catch (InterruptedException ex) {
                    }
                if (reconnectHandler != null) {
                    getLogger().info("Saving reconnect locations");
                    reconnectHandler.save();
                    reconnectHandler.close();
                    }
                saveThread.cancel();
                metricsThread.cancel();
                getLogger().info("Disabling plugins");
                for (Plugin plugin : Lists.reverse(new ArrayList < > (pluginManager.getPlugins()))) {
                    try {
                        plugin.onDisable();
                        for (Handler handler : plugin.getLogger().getHandlers()) {
                            handler.close();
                            }
                        }
                    catch (Throwable t) {
                        getLogger().log(Level.SEVERE, "Exception disabling plugin " + plugin.getDescription().getName(), t);
                        }
                    getScheduler().cancel(plugin);
                    plugin.getExecutorService().shutdownNow();
                    }
                getLogger().info("Thank you and goodbye");
                for (Handler handler : getLogger().getHandlers()) {
                    handler.close();
                    }
                System.exit(0);
                }
            }
        .start();
        }
    public void broadcast(DefinedPacket packet) {
        connectionLock.readLock().lock();
        try {
            for (UserConnection con : connections.values()) {
                con.unsafe().sendPacket(packet);
                }
            }
        finally {
            connectionLock.readLock().unlock();
            }
        }
    @Override public String getName() {
        return "BungeeCord";
        }
    @Override public String getVersion() {
        return (BungeeCord.class.getPackage().getImplementationVersion() == null) ? "unknown" : BungeeCord.class.getPackage().getImplementationVersion();
        }
    @Override public String getTranslation(String name, Object ... args) {
        String translation = "<translation '" + name + "' missing>";
        try {
            translation = MessageFormat.format(bundle.getString(name), args);
            }
        catch (MissingResourceException ex) {
            }
        return translation;
        }
    @Override @SuppressWarnings("unchecked") public Collection < ProxiedPlayer > getPlayers() {
        connectionLock.readLock().lock();
        try {
            return Collections.unmodifiableCollection(new HashSet(connections.values()));
            }
        finally {
            connectionLock.readLock().unlock();
            }
        }
    @Override public int getOnlineCount() {
        return connections.size();
        }
    @Override public ProxiedPlayer getPlayer(String name) {
        connectionLock.readLock().lock();
        try {
            return connections.get(name);
            }
        finally {
            connectionLock.readLock().unlock();
            }
        }
    public UserConnection getPlayerByOfflineUUID(UUID name) {
        connectionLock.readLock().lock();
        try {
            return connectionsByOfflineUUID.get(name);
            }
        finally {
            connectionLock.readLock().unlock();
            }
        }
    @Override public ProxiedPlayer getPlayer(UUID uuid) {
        connectionLock.readLock().lock();
        try {
            for (ProxiedPlayer proxiedPlayer : connections.values()) {
                if (proxiedPlayer.getUniqueId().equals(uuid)) {
                    return proxiedPlayer;
                    }
                }
            return null;
            }
        finally {
            connectionLock.readLock().unlock();
            }
        }
    @Override public Map < String, ServerInfo > getServers() {
        return config.getServers();
        }
    @Override public ServerInfo getServerInfo(String name) {
        return getServers().get(name);
        }
    @Override @Synchronized("pluginChannels") public void registerChannel(String channel) {
        pluginChannels.add(channel);
        }
    @Override @Synchronized("pluginChannels") public void unregisterChannel(String channel) {
        pluginChannels.remove(channel);
        }
    @Override @Synchronized("pluginChannels") public Collection < String > getChannels() {
        return Collections.unmodifiableCollection(pluginChannels);
        }
    public PluginMessage registerChannels() {
        return new PluginMessage("REGISTER", Util.format(pluginChannels, "\00").getBytes(Charsets.UTF_8), false);
        }
    @Override public int getProtocolVersion() {
        return Protocol.supportedVersions.get(Protocol.supportedVersions.size() - 1);
        }
    @Override public String getGameVersion() {
        return "1.8";
        }
    @Override public ServerInfo constructServerInfo(String name, InetSocketAddress address, String motd, boolean restricted) {
        return new BungeeServerInfo(name, address, motd, restricted);
        }
    @Override public CommandSender getConsole() {
        return ConsoleCommandSender.getInstance();
        }
    @Override public void broadcast(String message) {
        broadcast(TextComponent.fromLegacyText(message));
        }
    @Override public void broadcast(BaseComponent ... message) {
        getConsole().sendMessage(BaseComponent.toLegacyText(message));
        broadcast(new Chat(ComponentSerializer.toString(message)));
        }
    @Override public void broadcast(BaseComponent message) {
        getConsole().sendMessage(message.toLegacyText());
        broadcast(new Chat(ComponentSerializer.toString(message)));
        }
    public void addConnection(UserConnection con) {
        connectionLock.writeLock().lock();
        try {
            connections.put(con.getName(), con);
            connectionsByOfflineUUID.put(con.getPendingConnection().getOfflineId(), con);
            }
        finally {
            connectionLock.writeLock().unlock();
            }
        }
    public void removeConnection(UserConnection con) {
        connectionLock.writeLock().lock();
        try {
            if (connections.get(con.getName()) == con) {
                connections.remove(con.getName());
                connectionsByOfflineUUID.remove(con.getPendingConnection().getOfflineId());
                }
            }
        finally {
            connectionLock.writeLock().unlock();
            }
        }
    @Override public Collection < String > getDisabledCommands() {
        return config.getDisabledCommands();
        }
    @Override public Collection < ProxiedPlayer > matchPlayer(final String partialName) {
        Preconditions.checkNotNull(partialName, "partialName");
        ProxiedPlayer exactMatch = getPlayer(partialName);
        if (exactMatch != null) {
            return Collections.singleton(exactMatch);
            }
        return Sets.newHashSet(Iterables.find(getPlayers(), new Predicate < ProxiedPlayer > () {
            @Override public boolean apply(ProxiedPlayer input) {
                return (input == null) ? false : input.getName().toLowerCase().contains(partialName.toLowerCase());
                }
            }
        ));
        }
    @Override public Title createTitle() {
        return new BungeeTitle();
        }
    }
class BungeeCordLauncher {
    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();
        parser.allowsUnrecognizedOptions();
        parser.acceptsAll(Arrays.asList("v", "version"));
        OptionSet options = parser.parse(args);
        if (options.has("version")) {
            System.out.println(Bootstrap.class.getPackage().getImplementationVersion());
            return;
            }
        if (BungeeCord.class.getPackage().getSpecificationVersion() != null && System.getProperty("IReallyKnowWhatIAmDoingISwear") == null) {
            Date buildDate = new SimpleDateFormat("yyyyMMdd").parse(BungeeCord.class.getPackage().getSpecificationVersion());
            Calendar deadline = Calendar.getInstance();
            deadline.add(Calendar.WEEK_OF_YEAR, - 3);
            if (buildDate.before(deadline.getTime())) {
                System.err.println("*** Warning, this build is outdated ***");
                System.err.println("*** Please download a new build from http://ci.md-5.net/job/BungeeCord ***");
                System.err.println("*** You will get NO support regarding this build ***");
                System.err.println("*** Server will start in 30 seconds ***");
                Thread.sleep(TimeUnit.SECONDS.toMillis(30));
                }
            }
        BungeeCord bungee = new BungeeCord();
        ProxyServer.setInstance(bungee);
        bungee.getLogger().info("Enabled BungeeCord version " + bungee.getVersion());
        bungee.start();
        while (bungee.isRunning) {
            String line = bungee.getConsoleReader().readLine(">");
            if (line != null) {
                if (! bungee.getPluginManager().dispatchCommand(ConsoleCommandSender.getInstance(), line)) {
                    bungee.getConsole().sendMessage(ChatColor.RED + "Command not found");
                    }
                }
            }
        }
    }
class BungeeLogger extends Logger {
    private final Formatter formatter = new ConciseFormatter();
    private final LogDispatcher dispatcher = new LogDispatcher(this);
    @SuppressWarnings({
        "CallToPrintStackTrace", "CallToThreadStartDuringObjectConstruction" }
    ) @SuppressFBWarnings("SC_START_IN_CTOR") public BungeeLogger(BungeeCord bungee) {
        super ("BungeeCord", null);
        setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("proxy.log", 1 < < 24, 8, true);
            fileHandler.setFormatter(formatter);
            addHandler(fileHandler);
            ColouredWriter consoleHandler = new ColouredWriter(bungee.getConsoleReader());
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(formatter);
            addHandler(consoleHandler);
            }
        catch (IOException ex) {
            System.err.println("Could not register logger!");
            ex.printStackTrace();
            }
        dispatcher.start();
        }
    @Override public void log(LogRecord record) {
        dispatcher.queue(record);
        }
    void doLog(LogRecord record) {
        super.log(record);
        }
    }
class BungeeScheduler implements TaskScheduler {
    private final AtomicInteger taskCounter = new AtomicInteger();
    private final TIntObjectMap < BungeeTask > tasks = TCollections.synchronizedMap(new TIntObjectHashMap < BungeeTask > ());
    private final Multimap < Plugin, BungeeTask > tasksByPlugin = Multimaps.synchronizedMultimap(HashMultimap.< Plugin, BungeeTask > create());
    private final Unsafe unsafe = new Unsafe() {
        @Override public ExecutorService getExecutorService(Plugin plugin) {
            return plugin.getExecutorService();
            }
        }
    ;
    @Override public void cancel(int id) {
        BungeeTask task = tasks.remove(id);
        task.cancel();
        tasksByPlugin.values().remove(task);
        }
    @Override public void cancel(ScheduledTask task) {
        cancel(task.getId());
        }
    @Override public int cancel(Plugin plugin) {
        Set < ScheduledTask > toRemove = new HashSet < > ();
        for (ScheduledTask task : tasksByPlugin.get(plugin)) {
            toRemove.add(task);
            }
        for (ScheduledTask task : toRemove) {
            cancel(task);
            }
        return toRemove.size();
        }
    @Override public ScheduledTask runAsync(Plugin owner, Runnable task) {
        return schedule(owner, task, 0, TimeUnit.MILLISECONDS);
        }
    @Override public ScheduledTask schedule(Plugin owner, Runnable task, long delay, TimeUnit unit) {
        return schedule(owner, task, delay, 0, unit);
        }
    @Override public ScheduledTask schedule(Plugin owner, Runnable task, long delay, long period, TimeUnit unit) {
        Preconditions.checkNotNull(owner, "owner");
        Preconditions.checkNotNull(task, "task");
        BungeeTask prepared = new BungeeTask(this, taskCounter.getAndIncrement(), owner, task, delay, period, unit);
        tasks.put(prepared.getId(), prepared);
        owner.getExecutorService().execute(prepared);
        return prepared;
        }
    @Override public Unsafe unsafe() {
        return unsafe;
        }
    }
class BungeeSecurityManager extends SecurityManager {
    private static final boolean ENFORCE = false;
    private void checkRestricted(String text) {
        Class[] context = getClassContext();
        for (int i = 2;
        i < context.length;
        i ++) {
            ClassLoader loader = context[i].getClassLoader();
            if (loader == ClassLoader.getSystemClassLoader()) {
                break;
                }
            if (loader == null) {
                if (! context[i].getName().startsWith("java")) {
                    break;
                    }
                }
            if (loader != null) {
                AccessControlException ex = new AccessControlException("Plugin violation: " + text);
                if (ENFORCE) {
                    throw ex;
                    }
                ProxyServer.getInstance().getLogger().log(Level.WARNING, "Plugin performed restricted action, please inform them to use proper API methods: " + text, ex);
                break;
                }
            }
        }
    @Override public void checkExit(int status) {
        checkRestricted("Exit: Cannot close VM");
        }
    @Override public void checkAccess(ThreadGroup g) {
        if (! (g instanceof GroupedThreadFactory.BungeeGroup)) {
            checkRestricted("Illegal thread group access");
            }
        }
    @Override public void checkPermission(Permission perm, Object context) {
        checkPermission(perm);
        }
    @Override public void checkPermission(Permission perm) {
        switch (perm.getName()) {
            case "setSecurityManager" : throw new AccessControlException("Restricted Action", perm);
            }
        }
    }
@RequiredArgsConstructor @ToString(of = {
    "name", "address", "restricted" }
) class BungeeServerInfo implements ServerInfo {
    @Getter private final String name;
    @Getter private final InetSocketAddress address;
    private final Collection < ProxiedPlayer > players = new ArrayList < > ();
    @Getter private final String motd;
    @Getter private final boolean restricted;
    @Getter private final Queue < DefinedPacket > packetQueue = new LinkedList < > ();
    @Synchronized("players") public void addPlayer(ProxiedPlayer player) {
        players.add(player);
        }
    @Synchronized("players") public void removePlayer(ProxiedPlayer player) {
        players.remove(player);
        }
    @Synchronized("players") @Override public Collection < ProxiedPlayer > getPlayers() {
        return Collections.unmodifiableCollection(new HashSet(players));
        }
    @Override public boolean canAccess(CommandSender player) {
        Preconditions.checkNotNull(player, "player");
        return ! restricted || player.hasPermission("bungeecord.server." + name);
        }
    @Override public boolean equals(Object obj) {
        return (obj instanceof ServerInfo) && Objects.equals(getAddress(), ((ServerInfo) obj).getAddress());
        }
    @Override public int hashCode() {
        return address.hashCode();
        }
    @Override public void sendData(String channel, byte [] data) {
        sendData(channel, data, true);
        }
    @Override public boolean sendData(String channel, byte [] data, boolean queue) {
        Preconditions.checkNotNull(channel, "channel");
        Preconditions.checkNotNull(data, "data");
        synchronized (packetQueue) {
            Server server = (players.isEmpty()) ? null : players.iterator().next().getServer();
            if (server != null) {
                server.sendData(channel, data);
                return true;
                }
            else if (queue) {
                packetQueue.add(new PluginMessage(channel, data, false));
                }
            return false;
            }
        }
    @Override public void ping(final Callback < ServerPing > callback) {
        ping(callback, ProxyServer.getInstance().getProtocolVersion());
        }
    public void ping(final Callback < ServerPing > callback, final int protocolVersion) {
        Preconditions.checkNotNull(callback, "callback");
        ChannelFutureListener listener = new ChannelFutureListener() {
            @Override public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    future.channel().pipeline().get(HandlerBoss.class).setHandler(new PingHandler(BungeeServerInfo.this, callback, protocolVersion));
                    }
                else {
                    callback.done(null, future.cause());
                    }
                }
            }
        ;
        new Bootstrap().channel(PipelineUtils.getChannel()).group(BungeeCord.getInstance().eventLoops).handler(PipelineUtils.BASE).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000).remoteAddress(getAddress()).connect().addListener(listener);
        }
    }
@Data class BungeeTask implements Runnable, ScheduledTask {
    private final BungeeScheduler sched;
    private final int id;
    private final Plugin owner;
    private final Runnable task;
    private final long delay;
    private final long period;
    private final AtomicBoolean running = new AtomicBoolean(true);
    public BungeeTask(BungeeScheduler sched, int id, Plugin owner, Runnable task, long delay, long period, TimeUnit unit) {
        this.sched = sched;
        this.id = id;
        this.owner = owner;
        this.task = task;
        this.delay = unit.toMillis(delay);
        this.period = unit.toMillis(period);
        }
    @Override public void cancel() {
        running.set(false);
        }
    @Override public void run() {
        if (delay > 0) {
            try {
                Thread.sleep(delay);
                }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
            }
        while (running.get()) {
            try {
                task.run();
                }
            catch (Throwable t) {
                ProxyServer.getInstance().getLogger().log(Level.SEVERE, String.format("Task %s encountered an exception", this), t);
                }
            if (period <= 0) {
                break;
                }
            try {
                Thread.sleep(period);
                }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
            }
        sched.cancel(this);
        }
    }
class BungeeTitle implements Title {
    private net.md_5.bungee.protocol.packet.Title title, subtitle, times, clear, reset;
    private static net.md_5.bungee.protocol.packet.Title createPacket(Action action) {
        net.md_5.bungee.protocol.packet.Title title = new net.md_5.bungee.protocol.packet.Title();
        title.setAction(action);
        if (action == Action.TIMES) {
            title.setFadeIn(20);
            title.setStay(60);
            title.setFadeOut(20);
            }
        return title;
        }
    @Override public Title title(BaseComponent text) {
        if (title == null) {
            title = createPacket(Action.TITLE);
            }
        title.setText(ComponentSerializer.toString(text));
        return this;
        }
    @Override public Title title(BaseComponent ... text) {
        if (title == null) {
            title = createPacket(Action.TITLE);
            }
        title.setText(ComponentSerializer.toString(text));
        return this;
        }
    @Override public Title subTitle(BaseComponent text) {
        if (subtitle == null) {
            subtitle = createPacket(Action.SUBTITLE);
            }
        subtitle.setText(ComponentSerializer.toString(text));
        return this;
        }
    @Override public Title subTitle(BaseComponent ... text) {
        if (subtitle == null) {
            subtitle = createPacket(Action.SUBTITLE);
            }
        subtitle.setText(ComponentSerializer.toString(text));
        return this;
        }
    @Override public Title fadeIn(int ticks) {
        if (times == null) {
            times = createPacket(Action.TIMES);
            }
        times.setFadeIn(ticks);
        return this;
        }
    @Override public Title stay(int ticks) {
        if (times == null) {
            times = createPacket(Action.TIMES);
            }
        times.setStay(ticks);
        return this;
        }
    @Override public Title fadeOut(int ticks) {
        if (times == null) {
            times = createPacket(Action.TIMES);
            }
        times.setFadeOut(ticks);
        return this;
        }
    @Override public Title clear() {
        if (clear == null) {
            clear = createPacket(Action.CLEAR);
            }
        title = null;
        return this;
        }
    @Override public Title reset() {
        if (reset == null) {
            reset = createPacket(Action.RESET);
            }
        title = null;
        subtitle = null;
        times = null;
        return this;
        }
    private static void sendPacket(ProxiedPlayer player, DefinedPacket packet) {
        if (packet != null) {
            player.unsafe().sendPacket(packet);
            }
        }
    @Override public Title send(ProxiedPlayer player) {
        if (player.getPendingConnection().getVersion() >= ProtocolConstants.MINECRAFT_SNAPSHOT) {
            sendPacket(player, clear);
            sendPacket(player, reset);
            sendPacket(player, times);
            sendPacket(player, subtitle);
            sendPacket(player, title);
            }
        return this;
        }
    }
interface Callback < V > {
    public void done(V result, Throwable error);
    }
interface Cancellable {
    public boolean isCancelled();
    public void setCancelled(boolean cancel);
    }
@NoArgsConstructor(access = AccessLevel.PRIVATE) class CancelSendSignal extends Error {
    public static final CancelSendSignal INSTANCE = new CancelSendSignal();
    @Override public Throwable initCause(Throwable cause) {
        return this;
        }
    @Override public Throwable fillInStackTrace() {
        return this;
        }
    }
class CaseInsensitiveHashingStrategy implements HashingStrategy {
    static final CaseInsensitiveHashingStrategy INSTANCE = new CaseInsensitiveHashingStrategy();
    @Override public int computeHashCode(Object object) {
        return ((String) object).toLowerCase().hashCode();
        }
    @Override public boolean equals(Object o1, Object o2) {
        return o1.equals(o2) || (o1 instanceof String && o2 instanceof String && ((String) o1).toLowerCase().equals(((String) o2).toLowerCase()));
        }
    }
class CaseInsensitiveMap < V > extends TCustomHashMap < String, V > {
    public CaseInsensitiveMap() {
        super (CaseInsensitiveHashingStrategy.INSTANCE);
        }
    public CaseInsensitiveMap(Map < ? extends String, ? extends V > map) {
        super (CaseInsensitiveHashingStrategy.INSTANCE, map);
        }
    }
class CaseInsensitiveSet extends TCustomHashSet < String > {
    public CaseInsensitiveSet() {
        super (CaseInsensitiveHashingStrategy.INSTANCE);
        }
    public CaseInsensitiveSet(Collection < ? extends String > collection) {
        super (CaseInsensitiveHashingStrategy.INSTANCE, collection);
        }
    }
class CaseInsensitiveTest {
    @Test public void testMaps() {
        Object obj = new Object();
        CaseInsensitiveMap < Object > map = new CaseInsensitiveMap < > ();
        map.put("FOO", obj);
        Assert.assertTrue(map.contains("foo"));
        Assert.assertTrue(map.entrySet().iterator().next().getKey().equals("FOO"));
        map.remove("FoO");
        Assert.assertFalse(map.contains("foo"));
        }
    @Test public void testSets() {
        CaseInsensitiveSet set = new CaseInsensitiveSet();
        set.add("FOO");
        Assert.assertTrue(set.contains("foo"));
        set.remove("FoO");
        Assert.assertFalse(set.contains("foo"));
        }
    }
class ChannelWrapper {
    private final Channel ch;
    @Getter private volatile boolean closed;
    public ChannelWrapper(ChannelHandlerContext ctx) {
        this.ch = ctx.channel();
        }
    public void setProtocol(Protocol protocol) {
        ch.pipeline().get(MinecraftDecoder.class).setProtocol(protocol);
        ch.pipeline().get(MinecraftEncoder.class).setProtocol(protocol);
        }
    public void setVersion(int protocol) {
        ch.pipeline().get(MinecraftDecoder.class).setProtocolVersion(protocol);
        ch.pipeline().get(MinecraftEncoder.class).setProtocolVersion(protocol);
        }
    public void write(Object packet) {
        if (! closed) {
            if (packet instanceof PacketWrapper) {
                ((PacketWrapper) packet).setReleased(true);
                ch.write(((PacketWrapper) packet).buf, ch.voidPromise());
                }
            else {
                ch.write(packet, ch.voidPromise());
                }
            ch.flush();
            }
        }
    public void close() {
        if (! closed) {
            closed = true;
            ch.flush();
            ch.close();
            }
        }
    public void addBefore(String baseName, String name, ChannelHandler handler) {
        Preconditions.checkState(ch.eventLoop().inEventLoop(), "cannot add handler outside of event loop");
        ch.pipeline().flush();
        ch.pipeline().addBefore(baseName, name, handler);
        }
    public Channel getHandle() {
        return ch;
        }
    public void setCompressionThreshold(int compressionThreshold) {
        if (ch.pipeline().get(PacketCompressor.class) == null && compressionThreshold != - 1) {
            addBefore(PipelineUtils.PACKET_ENCODER, "compress", new PacketCompressor());
            }
        if (compressionThreshold != - 1) {
            ch.pipeline().get(PacketCompressor.class).setThreshold(compressionThreshold);
            }
        else {
            ch.pipeline().remove("compress");
            }
        if (ch.pipeline().get(PacketDecompressor.class) == null && compressionThreshold != - 1) {
            addBefore(PipelineUtils.PACKET_DECODER, "decompress", new PacketDecompressor());
            }
        if (compressionThreshold == - 1) {
            ch.pipeline().remove("decompress");
            }
        }
    }
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) class Chat extends DefinedPacket {
    private String message;
    private byte position;
    public Chat(String message) {
        this (message, (byte) 0);
        }
    @Override public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        message = readString(buf);
        if (direction == ProtocolConstants.Direction.TO_CLIENT && protocolVersion >= ProtocolConstants.MINECRAFT_SNAPSHOT) {
            position = buf.readByte();
            }
        }
    @Override public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        writeString(message, buf);
        if (direction == ProtocolConstants.Direction.TO_CLIENT && protocolVersion >= ProtocolConstants.MINECRAFT_SNAPSHOT) {
            buf.writeByte(position);
            }
        }
    @Override public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
        }
    }
enum ChatColor {
    BLACK('0', "black"), DARK_BLUE('1', "dark_blue"), DARK_GREEN('2', "dark_green"), DARK_AQUA('3', "dark_aqua"), DARK_RED('4', "dark_red"), DARK_PURPLE('5', "dark_purple"), GOLD('6', "gold"), GRAY('7', "gray"), DARK_GRAY('8', "dark_gray"), BLUE('9', "blue"), GREEN('a', "green"), AQUA('b', "aqua"), RED('c', "red"), LIGHT_PURPLE('d', "light_purple"), YELLOW('e', "yellow"), WHITE('f', "white"), MAGIC('k', "obfuscated"), BOLD('l', "bold"), STRIKETHROUGH('m', "strikethrough"), UNDERLINE('n', "underline"), ITALIC('o', "italic"), RESET('r', "reset");
    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf(COLOR_CHAR) + "[0-9A-FK-OR]");
    private static final Map < Character, ChatColor > BY_CHAR = new HashMap < > ();
    private final char code;
    private final String toString;
    @Getter private final String name;
    static {
        for (ChatColor colour : values()) {
            BY_CHAR.put(colour.code, colour);
            }
        }
    private ChatColor(char code, String name) {
        this.code = code;
        this.name = name;
        this.toString = new String(new char [] {
            COLOR_CHAR, code }
        );
        }
    @Override public String toString() {
        return toString;
        }
    public static String stripColor(final String input) {
        if (input == null) {
            return null;
            }
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
        }
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char [] b = textToTranslate.toCharArray();
        for (int i = 0;
        i < b.length - 1;
        i ++) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i + 1]) > - 1) {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
                }
            }
        return new String(b);
        }
    public static ChatColor getByChar(char code) {
        return BY_CHAR.get(code);
        }
    }
@Data @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true) class ChatEvent extends TargetedEvent implements Cancellable {
    private boolean cancelled;
    private String message;
    public ChatEvent(Connection sender, Connection receiver, String message) {
        super (sender, receiver);
        this.message = message;
        }
    public boolean isCommand() {
        return message.length() > 0 && message.charAt(0) == '/';
        }
    }
enum ChatMessageType {
    CHAT, SYSTEM, ACTION_BAR }
@RequiredArgsConstructor class CipherDecoder extends MessageToMessageDecoder < ByteBuf > {
    private final BungeeCipher cipher;
    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List < Object > out) throws Exception {
        out.add(cipher.cipher(ctx, msg));
        }
    @Override public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        cipher.free();
        }
    }
@RequiredArgsConstructor class CipherEncoder extends MessageToByteEncoder < ByteBuf > {
    private final BungeeCipher cipher;
    @Override protected void encode(ChannelHandlerContext ctx, ByteBuf in, ByteBuf out) throws Exception {
        cipher.cipher(in, out);
        }
    @Override public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        cipher.free();
        }
    }
@Getter @ToString @RequiredArgsConstructor final class ClickEvent {
    private final Action action;
    private final String value;
    public enum Action {
        OPEN_URL, OPEN_FILE, RUN_COMMAND, SUGGEST_COMMAND }
    }
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) class ClientSettings extends DefinedPacket {
    private String locale;
    private byte viewDistance;
    private byte chatFlags;
    private boolean chatColours;
    private byte difficulty;
    private byte skinParts;
    @Override public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        locale = readString(buf);
        viewDistance = buf.readByte();
        chatFlags = buf.readByte();
        chatColours = buf.readBoolean();
        if (protocolVersion <= ProtocolConstants.MINECRAFT_1_7_6) {
            difficulty = buf.readByte();
            }
        skinParts = buf.readByte();
        }
    @Override public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        writeString(locale, buf);
        buf.writeByte(viewDistance);
        buf.writeByte(chatFlags);
        buf.writeBoolean(chatColours);
        if (protocolVersion <= ProtocolConstants.MINECRAFT_1_7_6) {
            buf.writeByte(difficulty);
            }
        buf.writeByte(skinParts);
        }
    @Override public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
        }
    }
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) class ClientStatus extends DefinedPacket {
    private byte payload;
    @Override public void read(ByteBuf buf) {
        payload = buf.readByte();
        }
    @Override public void write(ByteBuf buf) {
        buf.writeByte(payload);
        }
    @Override public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
        }
    }
class ColouredWriter extends Handler {
    private final Map < ChatColor, String > replacements = new EnumMap < > (ChatColor.class);
    private final ChatColor[] colors = ChatColor.values();
    private final ConsoleReader console;
    public ColouredWriter(ConsoleReader console) {
        this.console = console;
        replacements.put(ChatColor.BLACK, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLACK).boldOff().toString());
        replacements.put(ChatColor.DARK_BLUE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLUE).boldOff().toString());
        replacements.put(ChatColor.DARK_GREEN, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.GREEN).boldOff().toString());
        replacements.put(ChatColor.DARK_AQUA, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.CYAN).boldOff().toString());
        replacements.put(ChatColor.DARK_RED, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.RED).boldOff().toString());
        replacements.put(ChatColor.DARK_PURPLE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.MAGENTA).boldOff().toString());
        replacements.put(ChatColor.GOLD, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.YELLOW).boldOff().toString());
        replacements.put(ChatColor.GRAY, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.WHITE).boldOff().toString());
        replacements.put(ChatColor.DARK_GRAY, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLACK).bold().toString());
        replacements.put(ChatColor.BLUE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.BLUE).bold().toString());
        replacements.put(ChatColor.GREEN, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.GREEN).bold().toString());
        replacements.put(ChatColor.AQUA, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.CYAN).bold().toString());
        replacements.put(ChatColor.RED, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.RED).bold().toString());
        replacements.put(ChatColor.LIGHT_PURPLE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.MAGENTA).bold().toString());
        replacements.put(ChatColor.YELLOW, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.YELLOW).bold().toString());
        replacements.put(ChatColor.WHITE, Ansi.ansi().a(Ansi.Attribute.RESET).fg(Ansi.Color.WHITE).bold().toString());
        replacements.put(ChatColor.MAGIC, Ansi.ansi().a(Ansi.Attribute.BLINK_SLOW).toString());
        replacements.put(ChatColor.BOLD, Ansi.ansi().a(Ansi.Attribute.UNDERLINE_DOUBLE).toString());
        replacements.put(ChatColor.STRIKETHROUGH, Ansi.ansi().a(Ansi.Attribute.STRIKETHROUGH_ON).toString());
        replacements.put(ChatColor.UNDERLINE, Ansi.ansi().a(Ansi.Attribute.UNDERLINE).toString());
        replacements.put(ChatColor.ITALIC, Ansi.ansi().a(Ansi.Attribute.ITALIC).toString());
        replacements.put(ChatColor.RESET, Ansi.ansi().a(Ansi.Attribute.RESET).toString());
        }
    public void print(String s) {
        for (ChatColor color : colors) {
            s = s.replaceAll("(?i)" + color.toString(), replacements.get(color));
            }
        try {
            console.print(ConsoleReader.RESET_LINE + s + Ansi.ansi().reset().toString());
            console.drawLine();
            console.flush();
            }
        catch (IOException ex) {
            }
        }
    @Override public void publish(LogRecord record) {
        if (isLoggable(record)) {
            print(getFormatter().format(record));
            }
        }
    @Override public void flush() {
        }
    @Override public void close() throws SecurityException {
        }
    }
@Data @RequiredArgsConstructor(access = AccessLevel.NONE) abstract class Command {
    private final String name;
    private final String permission;
    private final String[] aliases;
    public Command(String name) {
        this (name, null);
        }
    public Command(String name, String permission, String ... aliases) {
        Preconditions.checkArgument(name != null, "name");
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
        }
    public abstract void execute(CommandSender sender, String[] args);
    }
class CommandAlert extends Command {
    public CommandAlert() {
        super ("alert", "bungeecord.command.alert");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You must supply a message.");
            }
        else {
            StringBuilder builder = new StringBuilder();
            if (args[0].startsWith("&h")) {
                args[0] = args[0].substring(2, args[0].length());
                }
            else {
                builder.append(ProxyServer.getInstance().getTranslation("alert"));
                }
            for (String s : args) {
                builder.append(ChatColor.translateAlternateColorCodes('&', s));
                builder.append(" ");
                }
            String message = builder.substring(0, builder.length() - 1);
            ProxyServer.getInstance().broadcast(message);
            }
        }
    }
class CommandAlertRaw extends Command {
    public CommandAlertRaw() {
        super ("alertraw", "bungeecord.command.alert");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You must supply a message.");
            }
        else {
            String message = Joiner.on(' ').join(args);
            try {
                ProxyServer.getInstance().broadcast(ComponentSerializer.parse(message));
                }
            catch (Exception e) {
                Throwable error = e;
                while (error.getCause() != null) {
                    error = error.getCause();
                    }
                if (sender instanceof ProxiedPlayer) {
                    sender.sendMessage(new ComponentBuilder("An error occurred while parsing your message. (Hover for details)").color(ChatColor.RED).underlined(true).event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(error.getMessage()).color(ChatColor.RED).create())).create());
                    }
                else {
                    sender.sendMessage(new ComponentBuilder("An error occurred while parsing your message: ").color(ChatColor.RED).append(error.getMessage()).create());
                    }
                }
            }
        }
    }
class CommandBungee extends Command {
    public CommandBungee() {
        super ("bungee");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.BLUE + "This server is running BungeeCord version " + ProxyServer.getInstance().getVersion() + " by md_5");
        }
    }
class CommandEnd extends Command {
    public CommandEnd() {
        super ("end", "bungeecord.command.end");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        BungeeCord.getInstance().stop();
        }
    }
class CommandFind extends PlayerCommand {
    public CommandFind() {
        super ("find", "bungeecord.command.find");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Please follow this command by a user name");
            }
        else {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
            if (player == null || player.getServer() == null) {
                sender.sendMessage(ChatColor.RED + "That user is not online");
                }
            else {
                sender.sendMessage(ChatColor.BLUE + args[0] + " is online at " + player.getServer().getInfo().getName());
                }
            }
        }
    }
class CommandIP extends PlayerCommand {
    public CommandIP() {
        super ("ip", "bungeecord.command.ip");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Please follow this command by a user name");
            return;
            }
        ProxiedPlayer user = ProxyServer.getInstance().getPlayer(args[0]);
        if (user == null) {
            sender.sendMessage(ChatColor.RED + "That user is not online");
            }
        else {
            sender.sendMessage(ChatColor.BLUE + "IP of " + args[0] + " is " + user.getAddress());
            }
        }
    }
class CommandList extends Command {
    public CommandList() {
        super ("glist", "bungeecord.command.list");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        for (ServerInfo server : ProxyServer.getInstance().getServers().values()) {
            if (! server.canAccess(sender)) {
                continue;
                }
            List < String > players = new ArrayList < > ();
            for (ProxiedPlayer player : server.getPlayers()) {
                players.add(player.getDisplayName());
                }
            Collections.sort(players, String.CASE_INSENSITIVE_ORDER);
            sender.sendMessage(ProxyServer.getInstance().getTranslation("command_list", server.getName(), server.getPlayers().size(), Util.format(players, ChatColor.RESET + ", ")));
            }
        sender.sendMessage(ProxyServer.getInstance().getTranslation("total_players", ProxyServer.getInstance().getOnlineCount()));
        }
    }
class CommandPerms extends Command {
    public CommandPerms() {
        super ("perms");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        Set < String > permissions = new HashSet < > ();
        for (String group : sender.getGroups()) {
            permissions.addAll(ProxyServer.getInstance().getConfigurationAdapter().getPermissions(group));
            }
        sender.sendMessage(ChatColor.GOLD + "You have the following groups: " + Util.csv(sender.getGroups()));
        for (String permission : permissions) {
            sender.sendMessage(ChatColor.BLUE + "- " + permission);
            }
        }
    }
class CommandReload extends Command {
    public CommandReload() {
        super ("greload", "bungeecord.command.reload");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        BungeeCord.getInstance().config.load();
        BungeeCord.getInstance().stopListeners();
        BungeeCord.getInstance().startListeners();
        BungeeCord.getInstance().getPluginManager().callEvent(new ProxyReloadEvent(sender));
        sender.sendMessage(ChatColor.BOLD.toString() + ChatColor.RED.toString() + "BungeeCord has been reloaded." + " This is NOT advisable and you will not be supported with any issues that arise! Please restart BungeeCord ASAP.");
        }
    }
class CommandSend extends Command implements TabExecutor {
    public CommandSend() {
        super ("send", "bungeecord.command.send");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Not enough arguments, usage: /send <player|all|current> <target>");
            return;
            }
        ServerInfo target = ProxyServer.getInstance().getServerInfo(args[1]);
        if (target == null) {
            sender.sendMessage(ProxyServer.getInstance().getTranslation("no_server"));
            return;
            }
        if (args[0].equalsIgnoreCase("all")) {
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                summon(p, target, sender);
                }
            }
        else if (args[0].equalsIgnoreCase("current")) {
            if (! (sender instanceof ProxiedPlayer)) {
                sender.sendMessage(ChatColor.RED + "Only in game players can use this command");
                return;
                }
            ProxiedPlayer player = (ProxiedPlayer) sender;
            for (ProxiedPlayer p : player.getServer().getInfo().getPlayers()) {
                summon(p, target, sender);
                }
            }
        else {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online");
                return;
                }
            summon(player, target, sender);
            }
        sender.sendMessage(ChatColor.GREEN + "Successfully summoned player(s)");
        }
    private void summon(ProxiedPlayer player, ServerInfo target, CommandSender sender) {
        if (player.getServer() != null && ! player.getServer().getInfo().equals(target)) {
            player.connect(target);
            player.sendMessage(ChatColor.GOLD + "Summoned to " + target.getName() + " by " + sender.getName());
            }
        }
    @Override public Iterable < String > onTabComplete(CommandSender sender, String[] args) {
        if (args.length > 2 || args.length == 0) {
            return ImmutableSet.of();
            }
        Set < String > matches = new HashSet < > ();
        if (args.length == 1) {
            String search = args[0].toLowerCase();
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                if (player.getName().toLowerCase().startsWith(search)) {
                    matches.add(player.getName());
                    }
                }
            if ("all".startsWith(search)) {
                matches.add("all");
                }
            if ("current".startsWith(search)) {
                matches.add("current");
                }
            }
        else {
            String search = args[1].toLowerCase();
            for (String server : ProxyServer.getInstance().getServers().keySet()) {
                if (server.toLowerCase().startsWith(search)) {
                    matches.add(server);
                    }
                }
            }
        return matches;
        }
    }
interface CommandSender {
    public String getName();
    @Deprecated public void sendMessage(String message);
    @Deprecated public void sendMessages(String ... messages);
    public void sendMessage(BaseComponent ... message);
    public void sendMessage(BaseComponent message);
    public Collection < String > getGroups();
    public void addGroups(String ... groups);
    public void removeGroups(String ... groups);
    public boolean hasPermission(String permission);
    public void setPermission(String permission, boolean value);
    public Collection < String > getPermissions();
    }
class CommandServer extends Command implements TabExecutor {
    public CommandServer() {
        super ("server", "bungeecord.command.server");
        }
    @Override public void execute(CommandSender sender, String[] args) {
        if (! (sender instanceof ProxiedPlayer)) {
            return;
            }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        Map < String, ServerInfo > servers = ProxyServer.getInstance().getServers();
        if (args.length == 0) {
            player.sendMessage(ProxyServer.getInstance().getTranslation("current_server", player.getServer().getInfo().getName()));
            TextComponent serverList = new TextComponent(ProxyServer.getInstance().getTranslation("server_list"));
            serverList.setColor(ChatColor.GOLD);
            boolean first = true;
            for (ServerInfo server : servers.values()) {
                if (server.canAccess(player)) {
                    TextComponent serverTextComponent = new TextComponent(first ? server.getName() : ", " + server.getName());
                    int count = server.getPlayers().size();
                    serverTextComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(count + (count == 1 ? " player" : " players") + "\n").append("Click to connect to the server").italic(true).create()));
                    serverTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + server.getName()));
                    serverList.addExtra(serverTextComponent);
                    first = false;
                    }
                }
            player.sendMessage(serverList);
            }
        else {
            ServerInfo server = servers.get(args[0]);
            if (server == null) {
                player.sendMessage(ProxyServer.getInstance().getTranslation("no_server"));
                }
            else if (! server.canAccess(player)) {
                player.sendMessage(ProxyServer.getInstance().getTranslation("no_server_permission"));
                }
            else {
                player.connect(server);
                }
            }
        }
    @Override public Iterable < String > onTabComplete(final CommandSender sender, String[] args) {
        return (args.length != 0) ? Collections.EMPTY_LIST : Iterables.transform(Iterables.filter(ProxyServer.getInstance().getServers().values(), new Predicate < ServerInfo > () {
            @Override public boolean apply(ServerInfo input) {
                return input.canAccess(sender);
                }
            }
        ), new Function < ServerInfo, String > () {
            @Override public String apply(ServerInfo input) {
                return input.getName();
                }
            }
        );
        }
    }
class ComponentBuilder {
    private TextComponent current;
    private final List < BaseComponent > parts = new ArrayList < > ();
    public ComponentBuilder(ComponentBuilder original) {
        current = new TextComponent(original.current);
        for (BaseComponent baseComponent : original.parts) {
            parts.add(baseComponent.duplicate());
            }
        }
    public ComponentBuilder(String text) {
        current = new TextComponent(text);
        }
    public ComponentBuilder append(String text) {
        parts.add(current);
        current = new TextComponent(current);
        current.setText(text);
        return this;
        }
    public ComponentBuilder color(ChatColor color) {
        current.setColor(color);
        return this;
        }
    public ComponentBuilder bold(boolean bold) {
        current.setBold(bold);
        return this;
        }
    public ComponentBuilder italic(boolean italic) {
        current.setItalic(italic);
        return this;
        }
    public ComponentBuilder underlined(boolean underlined) {
        current.setUnderlined(underlined);
        return this;
        }
    public ComponentBuilder strikethrough(boolean strikethrough) {
        current.setStrikethrough(strikethrough);
        return this;
        }
    public ComponentBuilder obfuscated(boolean obfuscated) {
        current.setObfuscated(obfuscated);
        return this;
        }
    public ComponentBuilder event(ClickEvent clickEvent) {
        current.setClickEvent(clickEvent);
        return this;
        }
    public ComponentBuilder event(HoverEvent hoverEvent) {
        current.setHoverEvent(hoverEvent);
        return this;
        }
    public BaseComponent[] create() {
        parts.add(current);
        return parts.toArray(new BaseComponent[parts.size()]);
        }
    }
class ComponentSerializer implements JsonDeserializer < BaseComponent > {
    private final static Gson gson = new GsonBuilder().registerTypeAdapter(BaseComponent.class, new ComponentSerializer()).registerTypeAdapter(TextComponent.class, new TextComponentSerializer()).registerTypeAdapter(TranslatableComponent.class, new TranslatableComponentSerializer()).create();
    public final static ThreadLocal < HashSet < BaseComponent > > serializedComponents = new ThreadLocal < > ();
    public static BaseComponent[] parse(String json) {
        if (json.startsWith("[")) {
            return gson.fromJson(json, BaseComponent[].class);
            }
        return new BaseComponent[] {
            gson.fromJson(json, BaseComponent.class) }
        ;
        }
    public static String toString(BaseComponent component) {
        return gson.toJson(component);
        }
    public static String toString(BaseComponent ... components) {
        return gson.toJson(new TextComponent(components));
        }
    @Override public BaseComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive()) {
            return new TextComponent(json.getAsString());
            }
        JsonObject object = json.getAsJsonObject();
        if (object.has("translate")) {
            return context.deserialize(json, TranslatableComponent.class);
            }
        return context.deserialize(json, TextComponent.class);
        }
    }
class ComponentsTest {
    @Test public void testBasicComponent() {
        TextComponent textComponent = new TextComponent("Hello world");
        textComponent.setColor(ChatColor.RED);
        Assert.assertEquals("Hello world", textComponent.toPlainText());
        Assert.assertEquals(ChatColor.RED + "Hello world", textComponent.toLegacyText());
        }
    @Test public void testLegacyConverter() {
        BaseComponent[] test1 = TextComponent.fromLegacyText(ChatColor.AQUA + "Aqua " + ChatColor.RED + ChatColor.BOLD + "RedBold");
        Assert.assertEquals("Aqua RedBold", BaseComponent.toPlainText(test1));
        Assert.assertEquals(ChatColor.AQUA + "Aqua " + ChatColor.RED + ChatColor.BOLD + "RedBold", BaseComponent.toLegacyText(test1));
        BaseComponent[] test2 = TextComponent.fromLegacyText("Text http://spigotmc.org " + ChatColor.GREEN + "google.com/test");
        Assert.assertEquals("Text http://spigotmc.org google.com/test", BaseComponent.toPlainText(test2));
        Assert.assertEquals(ChatColor.WHITE + "Text " + ChatColor.WHITE + "http://spigotmc.org" + ChatColor.WHITE + " " + ChatColor.GREEN + "google.com/test", BaseComponent.toLegacyText(test2));
        ClickEvent url1 = test2[1].getClickEvent();
        Assert.assertNotNull(url1);
        Assert.assertTrue(url1.getAction() == ClickEvent.Action.OPEN_URL);
        Assert.assertEquals("http://spigotmc.org", url1.getValue());
        ClickEvent url2 = test2[3].getClickEvent();
        Assert.assertNotNull(url2);
        Assert.assertTrue(url2.getAction() == ClickEvent.Action.OPEN_URL);
        Assert.assertEquals("http://google.com/test", url2.getValue());
        }
    @Test public void testTranslateComponent() {
        TranslatableComponent item = new TranslatableComponent("item.swordGold.name");
        item.setColor(ChatColor.AQUA);
        TranslatableComponent translatableComponent = new TranslatableComponent("commands.give.success", item, "5", "thinkofdeath");
        Assert.assertEquals("Given Golden Sword * 5 to thinkofdeath", translatableComponent.toPlainText());
        Assert.assertEquals(ChatColor.WHITE + "Given " + ChatColor.AQUA + "Golden Sword" + ChatColor.WHITE + " * " + ChatColor.WHITE + "5" + ChatColor.WHITE + " to " + ChatColor.WHITE + "thinkofdeath", translatableComponent.toLegacyText());
        TranslatableComponent positional = new TranslatableComponent("book.pageIndicator", "5", "50");
        Assert.assertEquals("Page 5 of 50", positional.toPlainText());
        Assert.assertEquals(ChatColor.WHITE + "Page " + ChatColor.WHITE + "5" + ChatColor.WHITE + " of " + ChatColor.WHITE + "50", positional.toLegacyText());
        }
    @Test public void testBuilder() {
        BaseComponent[] components = new ComponentBuilder("Hello ").color(ChatColor.RED).append("World").bold(true).color(ChatColor.BLUE).append("!").color(ChatColor.YELLOW).create();
        Assert.assertEquals("Hello World!", BaseComponent.toPlainText(components));
        Assert.assertEquals(ChatColor.RED + "Hello " + ChatColor.BLUE + ChatColor.BOLD + "World" + ChatColor.YELLOW + ChatColor.BOLD + "!", BaseComponent.toLegacyText(components));
        }
    @Test(expected = IllegalArgumentException.class) public void testLoopSimple() {
        TextComponent component = new TextComponent("Testing");
        component.addExtra(component);
        ComponentSerializer.toString(component);
        }
    @Test(expected = IllegalArgumentException.class) public void testLoopComplex() {
        TextComponent a = new TextComponent("A");
        TextComponent b = new TextComponent("B");
        b.setColor(ChatColor.AQUA);
        TextComponent c = new TextComponent("C");
        c.setColor(ChatColor.RED);
        a.addExtra(b);
        b.addExtra(c);
        c.addExtra(a);
        ComponentSerializer.toString(a);
        }
    @Test public void testRepeated() {
        TextComponent a = new TextComponent("A");
        TextComponent b = new TextComponent("B");
        b.setColor(ChatColor.AQUA);
        a.addExtra(b);
        a.addExtra(b);
        ComponentSerializer.toString(a);
        }
    @Test(expected = IllegalArgumentException.class) public void testRepeatedError() {
        TextComponent a = new TextComponent("A");
        TextComponent b = new TextComponent("B");
        b.setColor(ChatColor.AQUA);
        TextComponent c = new TextComponent("C");
        c.setColor(ChatColor.RED);
        a.addExtra(b);
        a.addExtra(c);
        c.addExtra(a);
        a.addExtra(b);
        ComponentSerializer.toString(a);
        }
    }
class ConciseFormatter extends Formatter {
    private final DateFormat date = new SimpleDateFormat(System.getProperty("net.md_5.bungee.log-date-format", "HH:mm:ss"));
    @Override @SuppressWarnings("ThrowableResultIgnored") public String format(LogRecord record) {
        StringBuilder formatted = new StringBuilder();
        formatted.append(date.format(record.getMillis()));
        formatted.append(" [");
        formatted.append(record.getLevel().getLocalizedName());
        formatted.append("] ");
        formatted.append(formatMessage(record));
        formatted.append('\n');
        if (record.getThrown() != null) {
            StringWriter writer = new StringWriter();
            record.getThrown().printStackTrace(new PrintWriter(writer));
            formatted.append(writer);
            }
        return formatted.toString();
        }
    }
@Getter class Configuration implements ProxyConfig {
    private int timeout = 30000;
    private String uuid = UUID.randomUUID().toString();
    private Collection < ListenerInfo > listeners;
    private TMap < String, ServerInfo > servers;
    private boolean onlineMode = true;
    private int playerLimit = - 1;
    private Collection < String > disabledCommands;
    private int throttle = 4000;
    private boolean ipForward;
    private Favicon favicon;
    public void load() {
        ConfigurationAdapter adapter = ProxyServer.getInstance().getConfigurationAdapter();
        adapter.load();
        File fav = new File("server-icon.png");
        if (fav.exists()) {
            try {
                favicon = Favicon.create(ImageIO.read(fav));
                }
            catch (IOException | IllegalArgumentException ex) {
                ProxyServer.getInstance().getLogger().log(Level.WARNING, "Could not load server icon", ex);
                }
            }
        listeners = adapter.getListeners();
        timeout = adapter.getInt("timeout", timeout);
        uuid = adapter.getString("stats", uuid);
        onlineMode = adapter.getBoolean("online_mode", onlineMode);
        playerLimit = adapter.getInt("player_limit", playerLimit);
        throttle = adapter.getInt("connection_throttle", throttle);
        ipForward = adapter.getBoolean("ip_forward", ipForward);
        disabledCommands = new CaseInsensitiveSet((Collection < String >) adapter.getList("disabled_commands", Arrays.asList("disabledcommandhere")));
        Preconditions.checkArgument(listeners != null && ! listeners.isEmpty(), "No listeners defined.");
        Map < String, ServerInfo > newServers = adapter.getServers();
        Preconditions.checkArgument(newServers != null && ! newServers.isEmpty(), "No servers defined");
        if (servers == null) {
            servers = new CaseInsensitiveMap < > (newServers);
            }
        else {
            for (ServerInfo oldServer : servers.values()) {
                Preconditions.checkArgument(newServers.containsValue(oldServer), "Server %s removed on reload!", oldServer.getName());
                }
            for (Map.Entry < String, ServerInfo > newServer : newServers.entrySet()) {
                if (! servers.containsValue(newServer.getValue())) {
                    servers.put(newServer.getKey(), newServer.getValue());
                    }
                }
            }
        for (ListenerInfo listener : listeners) {
            Preconditions.checkArgument(servers.containsKey(listener.getDefaultServer()), "Default server %s is not defined", listener.getDefaultServer());
            Preconditions.checkArgument(servers.containsKey(listener.getFallbackServer()), "Fallback server %s is not defined", listener.getFallbackServer());
            for (String server : listener.getForcedHosts().values()) {
                if (! servers.containsKey(server)) {
                    ProxyServer.getInstance().getLogger().log(Level.WARNING, "Forced host server {0} is not defined", server);
                    }
                }
            }
        }
    @Override @Deprecated public String getFavicon() {
        return getFaviconObject().getEncoded();
        }
    @Override public Favicon getFaviconObject() {
        return favicon;
        }
    }
interface ConfigurationAdapter {
    public void load();
    public int getInt(String path, int def);
    public String getString(String path, String def);
    public boolean getBoolean(String path, boolean def);
    public Collection < ? > getList(String path, Collection < ? > def);
    public Map < String, ServerInfo > getServers();
    public Collection < ListenerInfo > getListeners();
    public Collection < String > getGroups(String player);
    public Collection < String > getPermissions(String group);
    }
abstract class ConfigurationProvider {
    private static final Map < Class < ? extends ConfigurationProvider >, ConfigurationProvider > providers = new HashMap < > ();
    static {
        providers.put(YamlConfiguration.class, new YamlConfiguration());
        }
    public static ConfigurationProvider getProvider(Class < ? extends ConfigurationProvider > provider) {
        return providers.get(provider);
        }
    public abstract void save(Configuration config, File file) throws IOException;
    public abstract void save(Configuration config, Writer writer);
    public abstract Configuration load(File file) throws IOException;
    public abstract Configuration load(File file, Configuration defaults) throws IOException;
    public abstract Configuration load(Reader reader);
    public abstract Configuration load(Reader reader, Configuration defaults);
    public abstract Configuration load(InputStream is);
    public abstract Configuration load(InputStream is, Configuration defaults);
    public abstract Configuration load(String string);
    public abstract Configuration load(String string, Configuration defaults);
    }
interface ConnectedPlayer extends ProxiedPlayer {
    }
interface Connection {
    InetSocketAddress getAddress();
    @Deprecated void disconnect(String reason);
    void disconnect(BaseComponent ... reason);
    void disconnect(BaseComponent reason);
    Unsafe unsafe();
    interface Unsafe {
        void sendPacket(DefinedPacket packet);
        }
    }
@RequiredArgsConstructor class ConnectionThrottle {
    private final Map < InetAddress, Long > throttle = new HashMap < > ();
    private final int throttleTime;
    public void unthrottle(InetAddress address) {
        throttle.remove(address);
        }
    public boolean throttle(InetAddress address) {
        Long value = throttle.get(address);
        long currentTime = System.currentTimeMillis();
        throttle.put(address, currentTime);
        return value != null && currentTime - value < throttleTime;
        }
    }
class ConsoleCommandSender implements CommandSender {
    @Getter private static final ConsoleCommandSender instance = new ConsoleCommandSender();
    private ConsoleCommandSender() {
        }
    @Override public void sendMessage(String message) {
        ProxyServer.getInstance().getLogger().info(message);
        }
    @Override public void sendMessages(String ... messages) {
        for (String message : messages) {
            sendMessage(message);
            }
        }
    @Override public void sendMessage(BaseComponent ... message) {
        sendMessage(BaseComponent.toLegacyText(message));
        }
    @Override public void sendMessage(BaseComponent message) {
        sendMessage(message.toLegacyText());
        }
    @Override public String getName() {
        return "CONSOLE";
        }
    @Override public Collection < String > getGroups() {
        return Collections.emptySet();
        }
    @Override public void addGroups(String ... groups) {
        throw new UnsupportedOperationException("Console may not have groups");
        }
    @Override public void removeGroups(String ... groups) {
        throw new UnsupportedOperationException("Console may not have groups");
        }
    @Override public boolean hasPermission(String permission) {
        return true;
        }
    @Override public void setPermission(String permission, boolean value) {
        throw new UnsupportedOperationException("Console has all permissions");
        }
    @Override public Collection < String > getPermissions() {
        return Collections.emptySet();
        }
    }
@RequiredArgsConstructor abstract class DefinedPacket {
    public static void writeString(String s, ByteBuf buf) {
        Preconditions.checkArgument(s.length() <= Short.MAX_VALUE, "Cannot send string longer than Short.MAX_VALUE (got %s characters)", s.length());
        byte [] b = s.getBytes(Charsets.UTF_8);
        writeVarInt(b.length, buf);
        buf.writeBytes(b);
        }
    public static String readString(ByteBuf buf) {
        int len = readVarInt(buf);
        Preconditions.checkArgument(len <= Short.MAX_VALUE, "Cannot receive string longer than Short.MAX_VALUE (got %s characters)", len);
        byte [] b = new byte [len];
        buf.readBytes(b);
        return new String(b, Charsets.UTF_8);
        }
    public static void writeArrayLegacy(byte [] b, ByteBuf buf, boolean allowExtended) {
        if (allowExtended) {
            Preconditions.checkArgument(b.length <= (Integer.MAX_VALUE & 0x1FFF9A), "Cannot send array longer than 2097050 (got %s bytes)", b.length);
            }
        else {
            Preconditions.checkArgument(b.length <= Short.MAX_VALUE, "Cannot send array longer than Short.MAX_VALUE (got %s bytes)", b.length);
            }
        writeVarShort(buf, b.length);
        buf.writeBytes(b);
        }
    public static byte [] readArrayLegacy(ByteBuf buf) {
        int len = readVarShort(buf);
        Preconditions.checkArgument(len <= (Integer.MAX_VALUE & 0x1FFF9A), "Cannot receive array longer than 2097050 (got %s bytes)", len);
        byte [] ret = new byte [len];
        buf.readBytes(ret);
        return ret;
        }
    public static void writeArray(byte [] b, ByteBuf buf) {
        writeVarInt(b.length, buf);
        buf.writeBytes(b);
        }
    public static byte [] readArray(ByteBuf buf) {
        byte [] ret = new byte [readVarInt(buf)];
        buf.readBytes(ret);
        return ret;
        }
    public static void writeStringArray(String[] s, ByteBuf buf) {
        writeVarInt(s.length, buf);
        for (String str : s) {
            writeString(str, buf);
            }
        }
    public static String[] readStringArray(ByteBuf buf) {
        int len = readVarInt(buf);
        String[] ret = new String[len];
        for (int i = 0;
        i < ret.length;
        i ++) {
            ret[i] = readString(buf);
            }
        return ret;
        }
    public static int readVarInt(ByteBuf input) {
        return readVarInt(input, 5);
        }
    public static int readVarInt(ByteBuf input, int maxBytes) {
        int out = 0;
        int bytes = 0;
        byte in;
        while (true) {
            in = input.readByte();
            out |= (in & 0x7F) < < (bytes ++ * 7);
            if (bytes > maxBytes) {
                throw new RuntimeException("VarInt too big");
                }
            if ((in & 0x80) != 0x80) {
                break;
                }
            }
        return out;
        }
    public static void writeVarInt(int value, ByteBuf output) {
        int part;
        while (true) {
            part = value & 0x7F;
            value >>>= 7;
            if (value != 0) {
                part |= 0x80;
                }
            output.writeByte(part);
            if (value == 0) {
                break;
                }
            }
        }
    public static int readVarShort(ByteBuf buf) {
        int low = buf.readUnsignedShort();
        int high = 0;
        if ((low & 0x8000) != 0) {
            low = low & 0x7FFF;
            high = buf.readUnsignedByte();
            }
        return ((high & 0xFF) < < 15) | low;
        }
    public static void writeVarShort(ByteBuf buf, int toWrite) {
        int low = toWrite & 0x7FFF;
        int high = (toWrite & 0x7F8000) > > 15;
        if (high != 0) {
            low = low | 0x8000;
            }
        buf.writeShort(low);
        if (high != 0) {
            buf.writeByte(high);
            }
        }
    public static void writeUUID(UUID value, ByteBuf output) {
        output.writeLong(value.getMostSignificantBits());
        output.writeLong(value.getLeastSignificantBits());
        }
    public static UUID readUUID(ByteBuf input) {
        return new UUID(input.readLong(), input.readLong());
        }
    public void read(ByteBuf buf) {
        throw new UnsupportedOperationException("Packet must implement read method");
        }
    public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        read(buf);
        }
    public void write(ByteBuf buf) {
        throw new UnsupportedOperationException("Packet must implement write method");
        }
    public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        write(buf);
        }
    public abstract void handle(AbstractPacketHandler handler) throws Exception;
    @Override public abstract boolean equals(Object obj);
    @Override public abstract int hashCode();
    @Override public abstract String toString();
    }
@RequiredArgsConstructor class DownstreamBridge extends PacketHandler {
    private final ProxyServer bungee;
    private final UserConnection con;
    private final ServerConnection server;
    @Override public void exception(Throwable t) throws Exception {
        if (server.isObsolete()) {
            return;
            }
        ServerInfo def = bungee.getServerInfo(con.getPendingConnection().getListener().getFallbackServer());
        if (server.getInfo() != def) {
            server.setObsolete(true);
            con.connectNow(def);
            con.sendMessage(bungee.getTranslation("server_went_down"));
            }
        else {
            con.disconnect(Util.exception(t));
            }
        }
    @Override public void disconnected(ChannelWrapper channel) throws Exception {
        server.getInfo().removePlayer(con);
        if (bungee.getReconnectHandler() != null) {
            bungee.getReconnectHandler().setServer(con);
            }
        if (! server.isObsolete()) {
            con.disconnect(bungee.getTranslation("lost_connection"));
            }
        ServerDisconnectEvent serverDisconnectEvent = new ServerDisconnectEvent(con, server.getInfo());
        bungee.getPluginManager().callEvent(serverDisconnectEvent);
        }
    @Override public void handle(PacketWrapper packet) throws Exception {
        if (! server.isObsolete()) {
            con.getEntityRewrite().rewriteClientbound(packet.buf, con.getServerEntityId(), con.getClientEntityId());
            con.sendPacket(packet);
            }
        }
    @Override public void handle(KeepAlive alive) throws Exception {
        con.setSentPingId(alive.getRandomId());
        con.setSentPingTime(System.currentTimeMillis());
        }
    @Override public void handle(PlayerListItem playerList) throws Exception {
        con.getTabListHandler().onUpdate(TabList.rewrite(playerList));
        throw CancelSendSignal.INSTANCE;
        }
    @Override public void handle(ScoreboardObjective objective) throws Exception {
        Scoreboard serverScoreboard = con.getServerSentScoreboard();
        switch (objective.getAction()) {
            case 0 : serverScoreboard.addObjective(new Objective(objective.getName(), objective.getValue(), objective.getType()));
            break;
            case 1 : serverScoreboard.removeObjective(objective.getName());
            break;
            case 2 : Objective oldObjective = serverScoreboard.getObjective(objective.getName());
            if (oldObjective != null) {
                oldObjective.setValue(objective.getValue());
                }
            break;
            default : throw new IllegalArgumentException("Unknown objective action: " + objective.getAction());
            }
        }
    @Override public void handle(ScoreboardScore score) throws Exception {
        Scoreboard serverScoreboard = con.getServerSentScoreboard();
        switch (score.getAction()) {
            case 0 : Score s = new Score(score.getItemName(), score.getScoreName(), score.getValue());
            serverScoreboard.removeScore(score.getItemName());
            serverScoreboard.addScore(s);
            break;
            case 1 : serverScoreboard.removeScore(score.getItemName());
            break;
            default : throw new IllegalArgumentException("Unknown scoreboard action: " + score.getAction());
            }
        }
    @Override public void handle(ScoreboardDisplay displayScoreboard) throws Exception {
        Scoreboard serverScoreboard = con.getServerSentScoreboard();
        serverScoreboard.setName(displayScoreboard.getName());
        serverScoreboard.setPosition(Position.values() [displayScoreboard.getPosition()]);
        }
    @Override public void handle(net.md_5.bungee.protocol.packet.Team team) throws Exception {
        Scoreboard serverScoreboard = con.getServerSentScoreboard();
        if (team.getMode() == 1) {
            serverScoreboard.removeTeam(team.getName());
            return;
            }
        Team t;
        if (team.getMode() == 0) {
            t = new Team(team.getName());
            serverScoreboard.addTeam(t);
            }
        else {
            t = serverScoreboard.getTeam(team.getName());
            }
        if (t != null) {
            if (team.getMode() == 0 || team.getMode() == 2) {
                t.setDisplayName(team.getDisplayName());
                t.setPrefix(team.getPrefix());
                t.setSuffix(team.getSuffix());
                t.setFriendlyFire(team.getFriendlyFire());
                t.setNameTagVisibility(team.getNameTagVisibility());
                t.setColor(team.getColor());
                }
            if (team.getPlayers() != null) {
                for (String s : team.getPlayers()) {
                    if (team.getMode() == 0 || team.getMode() == 3) {
                        t.addPlayer(s);
                        }
                    else {
                        t.removePlayer(s);
                        }
                    }
                }
            }
        }
    @Override public void handle(PluginMessage pluginMessage) throws Exception {
        DataInput in = pluginMessage.getStream();
        PluginMessageEvent event = new PluginMessageEvent(con.getServer(), con, pluginMessage.getTag(), pluginMessage.getData().clone());
        if (bungee.getPluginManager().callEvent(event).isCancelled()) {
            throw CancelSendSignal.INSTANCE;
            }
        if (pluginMessage.getTag().equals("MC|Brand")) {
            if (con.getPendingConnection().getVersion() >= ProtocolConstants.MINECRAFT_SNAPSHOT) {
                try {
                    ByteBuf brand = Unpooled.wrappedBuffer(pluginMessage.getData());
                    String serverBrand = DefinedPacket.readString(brand);
                    brand.release();
                    brand = ByteBufAllocator.DEFAULT.heapBuffer();
                    DefinedPacket.writeString(bungee.getName() + " (" + bungee.getVersion() + ")" + " <- " + serverBrand, brand);
                    pluginMessage.setData(brand.array().clone());
                    brand.release();
                    }
                catch (Exception ignored) {
                    return;
                    }
                }
            else {
                String serverBrand = new String(pluginMessage.getData(), "UTF-8");
                pluginMessage.setData((bungee.getName() + " (" + bungee.getVersion() + ")" + " <- " + serverBrand).getBytes("UTF-8"));
                }
            con.unsafe().sendPacket(pluginMessage);
            throw CancelSendSignal.INSTANCE;
            }
        if (pluginMessage.getTag().equals("BungeeCord")) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            String subChannel = in.readUTF();
            if (subChannel.equals("ForwardToPlayer")) {
                ProxiedPlayer target = bungee.getPlayer(in.readUTF());
                if (target != null) {
                    String channel = in.readUTF();
                    short len = in.readShort();
                    byte [] data = new byte [len];
                    in.readFully(data);
                    out.writeUTF(channel);
                    out.writeShort(data.length);
                    out.write(data);
                    byte [] payload = out.toByteArray();
                    target.getServer().sendData("BungeeCord", payload);
                    }
                out = null;
                }
            if (subChannel.equals("Forward")) {
                String target = in.readUTF();
                String channel = in.readUTF();
                short len = in.readShort();
                byte [] data = new byte [len];
                in.readFully(data);
                out.writeUTF(channel);
                out.writeShort(data.length);
                out.write(data);
                byte [] payload = out.toByteArray();
                out = null;
                if (target.equals("ALL")) {
                    for (ServerInfo server : bungee.getServers().values()) {
                        if (server != con.getServer().getInfo()) {
                            server.sendData("BungeeCord", payload);
                            }
                        }
                    }
                else if (target.equals("ONLINE")) {
                    for (ServerInfo server : bungee.getServers().values()) {
                        if (server != con.getServer().getInfo()) {
                            server.sendData("BungeeCord", payload, false);
                            }
                        }
                    }
                else {
                    ServerInfo server = bungee.getServerInfo(target);
                    if (server != null) {
                        server.sendData("BungeeCord", payload);
                        }
                    }
                }
            if (subChannel.equals("Connect")) {
                ServerInfo server = bungee.getServerInfo(in.readUTF());
                if (server != null) {
                    con.connect(server);
                    }
                }
            if (subChannel.equals("ConnectOther")) {
                ProxiedPlayer player = bungee.getPlayer(in.readUTF());
                if (player != null) {
                    ServerInfo server = bungee.getServerInfo(in.readUTF());
                    if (server != null) {
                        player.connect(server);
                        }
                    }
                }
            if (subChannel.equals("IP")) {
                out.writeUTF("IP");
                out.writeUTF(con.getAddress().getHostString());
                out.writeInt(con.getAddress().getPort());
                }
            if (subChannel.equals("PlayerCount")) {
                String target = in.readUTF();
                out.writeUTF("PlayerCount");
                if (target.equals("ALL")) {
                    out.writeUTF("ALL");
                    out.writeInt(bungee.getOnlineCount());
                    }
                else {
                    ServerInfo server = bungee.getServerInfo(target);
                    if (server != null) {
                        out.writeUTF(server.getName());
                        out.writeInt(server.getPlayers().size());
                        }
                    }
                }
            if (subChannel.equals("PlayerList")) {
                String target = in.readUTF();
                out.writeUTF("PlayerList");
                if (target.equals("ALL")) {
                    out.writeUTF("ALL");
                    out.writeUTF(Util.csv(bungee.getPlayers()));
                    }
                else {
                    ServerInfo server = bungee.getServerInfo(target);
                    if (server != null) {
                        out.writeUTF(server.getName());
                        out.writeUTF(Util.csv(server.getPlayers()));
                        }
                    }
                }
            if (subChannel.equals("GetServers")) {
                out.writeUTF("GetServers");
                out.writeUTF(Util.csv(bungee.getServers().keySet()));
                }
            if (subChannel.equals("Message")) {
                ProxiedPlayer target = bungee.getPlayer(in.readUTF());
                if (target != null) {
                    target.sendMessage(in.readUTF());
                    }
                }
            if (subChannel.equals("GetServer")) {
                out.writeUTF("GetServer");
                out.writeUTF(server.getInfo().getName());
                }
            if (subChannel.equals("UUID")) {
                out.writeUTF("UUID");
                out.writeUTF(con.getUUID());
                }
            if (subChannel.equals("UUIDOther")) {
                ProxiedPlayer player = bungee.getPlayer(in.readUTF());
                if (player != null) {
                    out.writeUTF("UUIDOther");
                    out.writeUTF(player.getName());
                    out.writeUTF(player.getUUID());
                    }
                }
            if (subChannel.equals("ServerIP")) {
                ServerInfo info = bungee.getServerInfo(in.readUTF());
                if (info != null) {
                    out.writeUTF("ServerIP");
                    out.writeUTF(info.getName());
                    out.writeUTF(info.getAddress().getAddress().getHostAddress());
                    out.writeShort(info.getAddress().getPort());
                    }
                }
            if (subChannel.equals("KickPlayer")) {
                ProxiedPlayer player = bungee.getPlayer(in.readUTF());
                if (player != null) {
                    String kickReason = in.readUTF();
                    player.disconnect(new TextComponent(kickReason));
                    }
                }
            if (out != null) {
                byte [] b = out.toByteArray();
                if (b.length != 0) {
                    con.getServer().sendData("BungeeCord", b);
                    }
                }
            }
        }
    @Override public void handle(Kick kick) throws Exception {
        ServerInfo def = bungee.getServerInfo(con.getPendingConnection().getListener().getFallbackServer());
        if (Objects.equals(server.getInfo(), def)) {
            def = null;
            }
        ServerKickEvent event = bungee.getPluginManager().callEvent(new ServerKickEvent(con, server.getInfo(), ComponentSerializer.parse(kick.getMessage()), def, ServerKickEvent.State.CONNECTED));
        if (event.isCancelled() && event.getCancelServer() != null) {
            con.connectNow(event.getCancelServer());
            }
        else {
            con.disconnect0(event.getKickReasonComponent());
            }
        server.setObsolete(true);
        throw CancelSendSignal.INSTANCE;
        }
    @Override public void handle(SetCompression setCompression) throws Exception {
        con.setCompressionThreshold(setCompression.getThreshold());
        server.getCh().setCompressionThreshold(setCompression.getThreshold());
        }
    @Override public String toString() {
        return "[" + con.getName() + "] <-> DownstreamBridge <-> [" + server.getInfo().getName() + "]";
        }
    }
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) class EncryptionRequest extends DefinedPacket {
    private String serverId;
    private byte [] publicKey;
    private byte [] verifyToken;
    @Override public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        serverId = readString(buf);
        if (protocolVersion < ProtocolConstants.MINECRAFT_SNAPSHOT) {
            publicKey = readArrayLegacy(buf);
            verifyToken = readArrayLegacy(buf);
            }
        else {
            publicKey = readArray(buf);
            verifyToken = readArray(buf);
            }
        }
    @Override public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        writeString(serverId, buf);
        if (protocolVersion < ProtocolConstants.MINECRAFT_SNAPSHOT) {
            writeArrayLegacy(publicKey, buf, false);
            writeArrayLegacy(verifyToken, buf, false);
            }
        else {
            writeArray(publicKey, buf);
            writeArray(verifyToken, buf);
            }
        }
    @Override public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
        }
    }
@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = false) class EncryptionResponse extends DefinedPacket {
    private byte [] sharedSecret;
    private byte [] verifyToken;
    @Override public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        if (protocolVersion < ProtocolConstants.MINECRAFT_SNAPSHOT) {
            sharedSecret = readArrayLegacy(buf);
            verifyToken = readArrayLegacy(buf);
            }
        else {
            sharedSecret = readArray(buf);
            verifyToken = readArray(buf);
            }
        }
    @Override public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion) {
        if (protocolVersion < ProtocolConstants.MINECRAFT_SNAPSHOT) {
            writeArrayLegacy(sharedSecret, buf, false);
            writeArrayLegacy(verifyToken, buf, false);
            }
        else {
            writeArray(sharedSecret, buf);
            writeArray(verifyToken, buf);
            }
        }
    @Override public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
        }
    }
class EncryptionUtil {
    private static final Random random = new Random();
    public static final KeyPair keys;
    @Getter private static final SecretKey secret = new SecretKeySpec(new byte [16], "AES");
    static {
        try {
            keys = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            }
        catch (NoSuchAlgorithmException ex) {
            throw new ExceptionInInitializerError(ex);
            }
        }
    public static EncryptionRequest encryptRequest() {
        String hash = Long.toString(random.nextLong(), 16);
        byte [] pubKey = keys.getPublic().getEncoded();
        byte [] verify = new byte [4];
        random.nextBytes(verify);
        return new EncryptionRequest(hash, pubKey, verify);
        }
    public static SecretKey getSecret(EncryptionResponse resp, EncryptionRequest request) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
        byte [] decrypted = cipher.doFinal(resp.getVerifyToken());
        if (! Arrays.equals(request.getVerifyToken(), decrypted)) {
            throw new IllegalStateException("Key pairs do not match!");
            }
        cipher.init(Cipher.DECRYPT_MODE, keys.getPrivate());
        return new SecretKeySpec(cipher.doFinal(resp.getSharedSecret()), "AES");
        }
    public static BungeeCipher getCipher(boolean forEncryption, SecretKey shared) throws GeneralSecurityException {
        BungeeCipher cipher;
        if (NativeCipher.isLoaded()) {
            cipher = new NativeCipher();
            }
        else {
            cipher = new FallbackCipher();
            }
        cipher.init(forEncryption, shared);
        return cipher;
        }
    public static PublicKey getPubkey(EncryptionRequest request) throws GeneralSecurityException {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(request.getPublicKey()));
        }
    public static byte [] encrypt(Key key, byte [] b) throws GeneralSecurityException {
        Cipher hasher = Cipher.getInstance("RSA");
        hasher.init(Cipher.ENCRYPT_MODE, key);
        return hasher.doFinal(b);
        }
    }
abstract class EntityMap {
    private final boolean [] clientboundInts = new boolean [256];
    private final boolean [] clientboundVarInts = new boolean [256];
    private final boolean [] serverboundInts = new boolean [256];
    private final boolean [] serverboundVarInts = new boolean [256];
    EntityMap() {
        }
    public static EntityMap getEntityMap(int version) {
        switch (version) {
            case ProtocolConstants.MINECRAFT_1_7_2 : return new EntityMap_1_7_2();
            case ProtocolConstants.MINECRAFT_1_7_6 : return new EntityMap_1_7_6();
            case ProtocolConstants.MINECRAFT_SNAPSHOT : return new EntityMap_14_21_b();
            }
        throw new RuntimeException("Version " + version + " has no entity map");
        }
    protected void addRewrite(int id, ProtocolConstants.Direction direction, boolean varint) {
        if (direction == ProtocolConstants.Direction.TO_CLIENT) {
            if (varint) {
                clientboundVarInts[id] = true;
                }
            else {
                clientboundInts[id] = true;
                }
            }
        else {
            if (varint) {
                serverboundVarInts[id] = true;
                }
            else {
                serverboundInts[id] = true;
                }
            }
        }
    public void rewriteServerbound(ByteBuf packet, int oldId, int newId) {
        rewrite(packet, oldId, newId, serverboundInts, serverboundVarInts);
        }
    public void rewriteClientbound(ByteBuf packet, int oldId, int newId) {
        rewrite(packet, oldId, newId, clientboundInts, clientboundVarInts);
        }
    protected static void rewriteInt(ByteBuf packet, int oldId, int newId, int offset) {
        int readId = packet.getInt(offset);
        if (readId == oldId) {
            packet.setInt(offset, newId);
            }
        else if (readId == newId) {
            packet.setInt(offset, oldId);
            }
        }
    @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE") protected static void rewriteVarInt(ByteBuf packet, int oldId, int newId, int offset) {
        int readId = DefinedPacket.readVarInt(packet);
        int readIdLength = packet.readerIndex() - offset;
        if (readId == oldId || readId == newId) {
            ByteBuf data = packet.slice().copy();
            packet.readerIndex(offset);
            packet.writerIndex(offset);
            DefinedPacket.writeVarInt(readId == oldId ? newId : oldId, packet);
            packet.writeBytes(data);
            data.release();
            }
        }
    private static void rewrite(ByteBuf packet, int oldId, int newId, boolean [] ints, boolean [] varints) {
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt(packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (ints[packetId]) {
            rewriteInt(packet, oldId, newId, readerIndex + packetIdLength);
            }
        else if (varints[packetId]) {
            rewriteVarInt(packet, oldId, newId, readerIndex + packetIdLength);
            }
        packet.readerIndex(readerIndex);
        }
    }
class EntityMap_14_21_b extends EntityMap {
    EntityMap_14_21_b() {
        addRewrite(0x04, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0A, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0B, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0C, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0D, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0E, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0F, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x10, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x11, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x12, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x14, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x15, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x16, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x17, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x18, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x19, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x1A, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1B, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1C, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x1D, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x1E, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x20, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x25, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x2C, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x43, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x49, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x02, ProtocolConstants.Direction.TO_SERVER, true);
        addRewrite(0x0B, ProtocolConstants.Direction.TO_SERVER, true);
        }
    @Override @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE") public void rewriteClientbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteClientbound(packet, oldId, newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt(packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (packetId == 0x0D) {
            DefinedPacket.readVarInt(packet);
            rewriteVarInt(packet, oldId, newId, packet.readerIndex());
            }
        else if (packetId == 0x1B) {
            rewriteInt(packet, oldId, newId, readerIndex + packetIdLength + 4);
            }
        else if (packetId == 0x13) {
            int count = DefinedPacket.readVarInt(packet);
            int [] ids = new int [count];
            for (int i = 0;
            i < count;
            i ++) {
                ids[i] = DefinedPacket.readVarInt(packet);
                }
            packet.readerIndex(readerIndex + packetIdLength);
            packet.writerIndex(readerIndex + packetIdLength);
            DefinedPacket.writeVarInt(count, packet);
            for (int id : ids) {
                if (id == oldId) {
                    id = newId;
                    }
                else if (id == newId) {
                    id = oldId;
                    }
                DefinedPacket.writeVarInt(id, packet);
                }
            }
        else if (packetId == 0x0E) {
            DefinedPacket.readVarInt(packet);
            int type = packet.readUnsignedByte();
            if (type == 60 || type == 90) {
                packet.skipBytes(14);
                int position = packet.readerIndex();
                int readId = packet.readInt();
                int changedId = - 1;
                if (readId == oldId) {
                    packet.setInt(position, newId);
                    changedId = newId;
                    }
                else if (readId == newId) {
                    packet.setInt(position, oldId);
                    changedId = oldId;
                    }
                if (changedId != - 1) {
                    if (changedId == 0 && readId != 0) {
                        packet.readerIndex(readerIndex);
                        packet.writerIndex(packet.readableBytes() - 6);
                        }
                    else if (changedId != 0 && readId == 0) {
                        packet.readerIndex(readerIndex);
                        packet.capacity(packet.readableBytes() + 6);
                        packet.writerIndex(packet.readableBytes() + 6);
                        }
                    }
                }
            }
        else if (packetId == 0x0C) {
            DefinedPacket.readVarInt(packet);
            int idLength = packet.readerIndex() - readerIndex - packetIdLength;
            UUID uuid = DefinedPacket.readUUID(packet);
            ProxiedPlayer player;
            if ((player = BungeeCord.getInstance().getPlayerByOfflineUUID(uuid)) != null) {
                int previous = packet.writerIndex();
                packet.readerIndex(readerIndex);
                packet.writerIndex(readerIndex + packetIdLength + idLength);
                DefinedPacket.writeUUID(player.getUniqueId(), packet);
                packet.writerIndex(previous);
                }
            }
        else if (packetId == 0x42) {
            int event = packet.readUnsignedByte();
            if (event == 1) {
                DefinedPacket.readVarInt(packet);
                rewriteInt(packet, oldId, newId, packet.readerIndex());
                }
            else if (event == 2) {
                int position = packet.readerIndex();
                rewriteVarInt(packet, oldId, newId, packet.readerIndex());
                packet.readerIndex(position);
                DefinedPacket.readVarInt(packet);
                rewriteInt(packet, oldId, newId, packet.readerIndex());
                }
            }
        packet.readerIndex(readerIndex);
        }
    @Override public void rewriteServerbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteServerbound(packet, oldId, newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt(packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (packetId == 0x18) {
            UUID uuid = DefinedPacket.readUUID(packet);
            ProxiedPlayer player;
            if ((player = BungeeCord.getInstance().getPlayer(uuid)) != null) {
                int previous = packet.writerIndex();
                packet.readerIndex(readerIndex);
                packet.writerIndex(readerIndex + packetIdLength);
                DefinedPacket.writeUUID(((UserConnection) player).getPendingConnection().getOfflineId(), packet);
                packet.writerIndex(previous);
                }
            }
        packet.readerIndex(readerIndex);
        }
    }
class EntityMap_1_7_2 extends EntityMap {
    EntityMap_1_7_2() {
        addRewrite(0x04, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x0A, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x0B, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0C, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0D, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x0E, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x0F, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x10, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x11, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x12, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x14, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x15, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x16, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x17, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x18, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x19, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1A, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1B, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1C, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1D, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x1E, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x20, ProtocolConstants.Direction.TO_CLIENT, false);
        addRewrite(0x25, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x2C, ProtocolConstants.Direction.TO_CLIENT, true);
        addRewrite(0x02, ProtocolConstants.Direction.TO_SERVER, false);
        addRewrite(0x0A, ProtocolConstants.Direction.TO_SERVER, false);
        addRewrite(0x0B, ProtocolConstants.Direction.TO_SERVER, false);
        }
    @Override public void rewriteClientbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteClientbound(packet, oldId, newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt(packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (packetId == 0x0D || packetId == 0x1B) {
            rewriteInt(packet, oldId, newId, readerIndex + packetIdLength + 4);
            }
        else if (packetId == 0x13) {
            int count = packet.getByte(packetIdLength);
            for (int i = 0;
            i < count;
            i ++) {
                rewriteInt(packet, oldId, newId, packetIdLength + 1 + i * 4);
                }
            }
        else if (packetId == 0x0E) {
            DefinedPacket.readVarInt(packet);
            int type = packet.readUnsignedByte();
            if (type == 60 || type == 90) {
                packet.skipBytes(14);
                int position = packet.readerIndex();
                int readId = packet.readInt();
                int changedId = - 1;
                if (readId == oldId) {
                    packet.setInt(position, newId);
                    changedId = newId;
                    }
                else if (readId == newId) {
                    packet.setInt(position, oldId);
                    changedId = oldId;
                    }
                if (changedId != - 1) {
                    if (changedId == 0 && readId != 0) {
                        packet.readerIndex(readerIndex);
                        packet.writerIndex(packet.readableBytes() - 6);
                        }
                    else if (changedId != 0 && readId == 0) {
                        packet.readerIndex(readerIndex);
                        packet.capacity(packet.readableBytes() + 6);
                        packet.writerIndex(packet.readableBytes() + 6);
                        }
                    }
                }
            }
        packet.readerIndex(readerIndex);
        }
    }
class EntityMap_1_7_6 extends EntityMap_1_7_2 {
    @Override @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE") public void rewriteClientbound(ByteBuf packet, int oldId, int newId) {
        super.rewriteClientbound(packet, oldId, newId);
        int readerIndex = packet.readerIndex();
        int packetId = DefinedPacket.readVarInt(packet);
        int packetIdLength = packet.readerIndex() - readerIndex;
        if (packetId == 0x0C) {
            DefinedPacket.readVarInt(packet);
            int idLength = packet.readerIndex() - readerIndex - packetIdLength;
            String uuid = DefinedPacket.readString(packet);
            String username = DefinedPacket.readString(packet);
            int props = DefinedPacket.readVarInt(packet);
            if (props == 0) {
                UserConnection player = (UserConnection) BungeeCord.getInstance().getPlayer(username);
                if (player != null) {
                    LoginResult profile = player.getPendingConnection().getLoginProfile();
                    if (profile != null && profile.getProperties() != null && profile.getProperties().length >= 1) {
                        ByteBuf rest = packet.slice().copy();
                        packet.readerIndex(readerIndex);
                        packet.writerIndex(readerIndex + packetIdLength + idLength);
                        DefinedPacket.writeString(player.getUniqueId().toString(), packet);
                        DefinedPacket.writeString(username, packet);
                        DefinedPacket.writeVarInt(profile.getProperties().length, packet);
                        for (LoginResult.Property property : profile.getProperties()) {
                            DefinedPacket.writeString(property.getName(), packet);
                            DefinedPacket.writeString(property.getValue(), packet);
                            DefinedPacket.writeString(property.getSignature(), packet);
                            }
                        packet.writeBytes(rest);
                        rest.release();
                        }
                    }
                }
            }
        packet.readerIndex(readerIndex);
        }
    }
abstract class Event {
    public void postCall() {
        }
    }
class EventBus {
    private final Map < Class < ? >, Map < Byte, Map < Object, Method[] > > > byListenerAndPriority = new HashMap < > ();
    private final Map < Class < ? >, EventHandlerMethod[] > byEventBaked = new HashMap < > ();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Logger logger;
    public EventBus() {
        this (null);
        }
    public EventBus(Logger logger) {
        this.logger = (logger == null) ? Logger.getGlobal() : logger;
        }
    public void post(Object event) {
        lock.readLock().lock();
        try {
            EventHandlerMethod[] handlers = byEventBaked.get(event.getClass());
            if (handlers != null) {
                for (EventHandlerMethod method : handlers) {
                    try {
                        method.invoke(event);
                        }
                    catch (IllegalAccessException ex) {
                        throw new Error("Method became inaccessible: " + event, ex);
                        }
                    catch (IllegalArgumentException ex) {
                        throw new Error("Method rejected target/argument: " + event, ex);
                        }
                    catch (InvocationTargetException ex) {
                        logger.log(Level.WARNING, MessageFormat.format("Error dispatching event {0} to listener {1}", event, method.getListener()), ex.getCause());
                        }
                    }
                }
            }
        finally {
            lock.readLock().unlock();
            }
        }
    private Map < Class < ? >, Map < Byte, Set < Method > > > findHandlers(Object listener) {
        Map < Class < ? >, Map < Byte, Set < Method > > > handler = new HashMap < > ();
        for (Method m : listener.getClass().getDeclaredMethods()) {
            EventHandler annotation = m.getAnnotation(EventHandler.class);
            if (annotation != null) {
                Class < ? > [] params = m.getParameterTypes();
                if (params.length != 1) {
                    logger.log(Level.INFO, "Method {0} in class {1} annotated with {2} does not have single argument", new Object[] {
                        m, listener.getClass(), annotation }
                    );
                    continue;
                    }
                Map < Byte, Set < Method > > prioritiesMap = handler.get(params[0]);
                if (prioritiesMap == null) {
                    prioritiesMap = new HashMap < > ();
                    handler.put(params[0], prioritiesMap);
                    }
                Set < Method > priority = prioritiesMap.get(annotation.priority());
                if (priority == null) {
                    priority = new HashSet < > ();
                    prioritiesMap.put(annotation.priority(), priority);
                    }
                priority.add(m);
                }
            }
        return handler;
        }
    public void register(Object listener) {
        Map < Class < ? >, Map < Byte, Set < Method > > > handler = findHandlers(listener);
        lock.writeLock().lock();
        try {
            for (Map.Entry < Class < ? >, Map < Byte, Set < Method > > > e : handler.entrySet()) {
                Map < Byte, Map < Object, Method[] > > prioritiesMap = byListenerAndPriority.get(e.getKey());
                if (prioritiesMap == null) {
                    prioritiesMap = new HashMap < > ();
                    byListenerAndPriority.put(e.getKey(), prioritiesMap);
                    }
                for (Map.Entry < Byte, Set < Method > > entry : e.getValue().entrySet()) {
                    Map < Object, Method[] > currentPriorityMap = prioritiesMap.get(entry.getKey());
                    if (currentPriorityMap == null) {
                        currentPriorityMap = new HashMap < > ();
                        prioritiesMap.put(entry.getKey(), currentPriorityMap);
                        }
                    Method[] baked = new Method[entry.getValue().size()];
                    currentPriorityMap.put(listener, entry.getValue().toArray(baked));
                    }
                bakeHandlers(e.getKey());
                }
            }
        finally {
            lock.writeLock().unlock();
            }
        }
    public void unregister(Object listener) {
        Map < Class < ? >, Map < Byte, Set < Method > > > handler = findHandlers(listener);
        lock.writeLock().lock();
        try {
            for (Map.Entry < Class < ? >, Map < Byte, Set < Method > > > e : handler.entrySet()) {
                Map < Byte, Map < Object, Method[] > > prioritiesMap = byListenerAndPriority.get(e.getKey());
                if (prioritiesMap != null) {
                    for (Byte priority : e.getValue().keySet()) {
                        Map < Object, Method[] > currentPriority = prioritiesMap.get(priority);
                        if (currentPriority != null) {
                            currentPriority.remove(listener);
                            if (currentPriority.isEmpty()) {
                                prioritiesMap.remove(priority);
                                }
                            }
                        }
                    if (prioritiesMap.isEmpty()) {
                        byListenerAndPriority.remove(e.getKey());
                        }
                    }
                bakeHandlers(e.getKey());
                }
            }
        finally {
            lock.writeLock().unlock();
            }
        }
    private void bakeHandlers(Class < ? > eventClass) {
        Map < Byte, Map < Object, Method[] > > handlersByPriority = byListenerAndPriority.get(eventClass);
        if (handlersByPriority != null) {
            List < EventHandlerMethod > handlersList = new ArrayList < > (handlersByPriority.size() * 2);
            byte value = Byte.MIN_VALUE;
            do {
                Map < Object, Method[] > handlersByListener = handlersByPriority.get(value);
                if (handlersByListener != null) {
                    for (Map.Entry < Object, Method[] > listenerHandlers : handlersByListener.entrySet()) {
                        for (Method method : listenerHandlers.getValue()) {
                            EventHandlerMethod ehm = new EventHandlerMethod(listenerHandlers.getKey(), method);
                            handlersList.add(ehm);
                            }
                        }
                    }
                }
            while (value ++ < Byte.MAX_VALUE);
            byEventBaked.put(eventClass, handlersList.toArray(new EventHandlerMethod[handlersList.size()]));
            }
        else {
            byEventBaked.put(eventClass, null);
            }
        }
    }
class EventBusTest {
    private final EventBus bus = new EventBus();
    private final CountDownLatch latch = new CountDownLatch(2);
    @Test public void testNestedEvents() {
        bus.register(this);
        bus.post(new FirstEvent());
        Assert.assertEquals(0, latch.getCount());
        }
    @EventHandler public void firstListener(FirstEvent event) {
        bus.post(new SecondEvent());
        Assert.assertEquals(1, latch.getCount());
        latch.countDown();
        }
    @EventHandler public void secondListener(SecondEvent event) {
        latch.countDown();
        }
    public static class FirstEvent {
        }
    public static class SecondEvent {
        }
    }
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD) @interface EventHandler {
    byte priority() default EventPriority.NORMAL;
    }
@AllArgsConstructor class EventHandlerMethod {
    @Getter private final Object listener;
    @Getter private final Method method;
    public void invoke(Object event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        method.invoke(listener, event);
        }
    }
@NoArgsConstructor(access = AccessLevel.PRIVATE) class EventPriority {
    public static final byte LOWEST = - 64;
    public static final byte LOW = - 32;
    public static final byte NORMAL = 0;
    public static final byte HIGH = 32;
    public static final byte HIGHEST = 64;
    }
class EventPriorityTest {
    private final EventBus bus = new EventBus();
    private final CountDownLatch latch = new CountDownLatch(5);
    @Test public void testPriority() {
        bus.register(this);
        bus.register(new EventPriorityListenerPartner());
        bus.post(new PriorityTestEvent());
        Assert.assertEquals(0, latch.getCount());
        }
    @EventHandler(priority = Byte.MIN_VALUE) public void onMinPriority(PriorityTestEvent event) {
        Assert.assertEquals(5, latch.getCount());
        latch.countDown();
        }
    @EventHandler(priority = EventPriority.LOWEST) public void onLowestPriority(PriorityTestEvent event) {
        Assert.assertEquals(4, latch.getCount());
        latch.countDown();
        }
    @EventHandler public void onNormalPriority(PriorityTestEvent event) {
        Assert.assertEquals(3, latch.getCount());
        latch.countDown();
        }
    @EventHandler(priority = EventPriority.HIGHEST) public void onHighestPriority(PriorityTestEvent event) {
        Assert.assertEquals(2, latch.getCount());
        latch.countDown();
        }
    @EventHandler(priority = Byte.MAX_VALUE) public void onMaxPriority(PriorityTestEvent event) {
        Assert.assertEquals(1, latch.getCount());
        latch.countDown();
        }
    public static class PriorityTestEvent {
        }
    public class EventPriorityListenerPartner {
        @EventHandler(priority = EventPriority.HIGH) public void onHighPriority(PriorityTestEvent event) {
            Assert.assertEquals(2, latch.getCount());
            latch.countDown();
            }
        @EventHandler(priority = EventPriority.LOW) public void onLowPriority(PriorityTestEvent event) {
            Assert.assertEquals(4, latch.getCount());
            latch.countDown();
            }
        }
    }
