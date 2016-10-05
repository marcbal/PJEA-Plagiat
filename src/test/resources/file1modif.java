

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import fr.univ_artois.iut_lens.mexican_train.game.Domino;
import fr.univ_artois.iut_lens.mexican_train.game.GameSettings;

public class Application {
	public static final int BUTTON_QUIT = -1;		// enclanche l'arrêt du jeu
	public static final int SCR_INDEX = 0;			// l'accueil du jeu
	public static final int SCR_GAME = 1;			// le jeu en lui même
	public static final int SCR_PLAYER_CHOICE = 2;	// les choix des joueurs avec pseudos / IA
	
	
	private Clock clock = new Clock();
	private RenderWindow window = new RenderWindow();
	private Vector2i windowSize;
	private Screen[] screens = new Screen[3];
	private Background background;
	private ButtonValue activScreen = new ButtonValue(SCR_INDEX);
	private Time timeBefore = clock.getElapsedTime();
	
	private ScreenDebug debugScreen;
	
	private GameSettings gameSettings = new GameSettings(4, 1);
	
	
	
	
	public Application()
	{
		System.out.println("Chargement des ressources ...");
		// précharge la police de caractère
		Widget.getThemeFont();
		// crée la première instance de WDomino pour précharger les texture des dominos
		new WDomino(new Vector2f(0, 0), new Vector2f(0, 0), false, new Domino(0, 0), false, false);
		System.out.println("Chargement des ressources terminée.");
		
		
		windowSize = new Vector2i(1024, 576);
		System.out.println("Démarrage de l'interface graphique : Bibliothèque JSFML http://www.jsfml.org/");
		window.create(new VideoMode(windowSize.x, windowSize.y), "Le Train Mexicain !", WindowStyle.TITLEBAR | WindowStyle.CLOSE, new ContextSettings(8));
		window.setFramerateLimit(60);
		
		// ---- crée l'icone de la fenêtre ----
		Image icon = new Image();
		Path iconPath = FileSystems.getDefault().getPath("ressources", "icone.png");
		try {
			icon.loadFromFile(iconPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.setIcon(icon);
		// ------------------------------------
		
		background = new Background(windowSize);
		debugScreen = new ScreenDebug(windowSize);
		
		screens[SCR_INDEX] = new ScreenIndex(windowSize, activScreen);
		screens[SCR_GAME] = new ScreenGame(windowSize, activScreen, gameSettings);
		screens[SCR_PLAYER_CHOICE] = new ScreenPlayerChoice(windowSize, activScreen, gameSettings);
		
		System.out.println("Démarrage du jeu terminé ("+Math.round(clock.getElapsedTime().asSeconds()*100)/100.0+" secondes)");
	}
	
	
	public void run()
	{
		// boucle principale
		while (window.isOpen())
		{
			handleEvents();
			if (activScreen.val == BUTTON_QUIT)
	            return;
			update();
			draw();
		}
	}
	
	
	
	
	
	protected void handleEvents()
	{
		for(Event event : window.pollEvents())
		{
	        if(event.type == Event.Type.CLOSED)
	            window.close();
	        else if (activScreen.val == BUTTON_QUIT)
	        {
	            window.close();
	            return;
	        }
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F3)
	        	debugScreen.toggleDebugger();
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F4)
	        	background.toggleEnable();
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F5)
	        	background.toggleAnimate();
	        else
	        	screens[activScreen.val].onEvent(event);
	    }
	}
	
	
	
	protected void update()
	{
		Time now = clock.getElapsedTime();
		Time elapsed = Time.sub(now, timeBefore);
		timeBefore = now;
		
		background.update(elapsed);
		
		screens[activScreen.val].update(elapsed);
		
		debugScreen.update(elapsed);
		
		
	}
	
	/*
	 * Commentaire random
	 */
	
	protected void draw()
	{
		window.clear(Color.WHITE);
		
		window.draw(background);
		
		window.draw(screens[activScreen.val]);
		
		window.draw(debugScreen);
		
		window.display();
	}
	
}