 package fr.univ_artois.iut_lens.mexican_train.gui;

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
	
	
	private Clock _clock = new Clock();
	private RenderWindow _window = new RenderWindow();   
	private Vector2i _window_size;  
	private Screen[] _ecrans = new Screen[3];
	private Background _background;
	private ButtonValue _ecran_actif = new ButtonValue(SCR_INDEX);
	private Time _timeBefore = _clock.getElapsedTime(); 
	
	private ScreenDebug _debug;
	
	private GameSettings _gameSettings = new GameSettings(4, 1);
	
	
	
	
	public Application()	
	{	 
		System.out.println("Chargement des ressources ...");
		// précharge la police de caractère
		Widget.getThemeFont();
		// crée la première instance de WDomino pour précharger les texture des dominos
		new WDomino(new Vector2f(0, 0), new Vector2f(0, 0), false, new Domino(0, 0), false, false);
		System.out.println("Chargement des ressources terminée.");
		
		
		_window_size = new Vector2i(1024, 576);
		System.out.println("Démarrage de l'interface graphique : Bibliothèque JSFML http://www.jsfml.org/");
		_window.create(new VideoMode(_window_size.x, _window_size.y), "Le Train Mexicain !", WindowStyle.TITLEBAR | WindowStyle.CLOSE, new ContextSettings(8));
		_window.setFramerateLimit(60);
		
		// ---- crée l'icone de la fenêtre ----
		Image icone = new Image();
		Path icone_path = FileSystems.getDefault().getPath("ressources", "icone.png");
		try {
			icone.loadFromFile(icone_path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_window.setIcon(icone);
		// ------------------------------------
		
		_background = new Background(_window_size);
		_debug = new ScreenDebug(_window_size);
		
		_ecrans[SCR_INDEX] = new ScreenIndex(_window_size, _ecran_actif);
		_ecrans[SCR_GAME] = new ScreenGame(_window_size, _ecran_actif, _gameSettings);
		_ecrans[SCR_PLAYER_CHOICE] = new ScreenPlayerChoice(_window_size, _ecran_actif, _gameSettings);
		
		System.out.println("Démarrage du jeu terminé ("+Math.round(_clock.getElapsedTime().asSeconds()*100)/100.0+" secondes)");
	}
	
	
	
	
	
	
	
	
	public void run()
	{
		// boucle principale
		while (_window.isOpen())
		{
			handleEvents();
			if (_ecran_actif.val == BUTTON_QUIT)
	            return;
			update();
			draw();
		}
	}
	
	
	
	
	
	
	
	
	
	
	protected void handleEvents()
	{
		for(Event event : _window.pollEvents())
		{
	        if(event.type == Event.Type.CLOSED)
	            _window.close();
	        else if (_ecran_actif.val == BUTTON_QUIT)
	        {
	            _window.close();
	            return;
	        }
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F3)
	        	_debug.toggleDebugger();
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F4)
	        	_background.toggleEnable();
	        else if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Key.F5)
	        	_background.toggleAnimate();
	        else
	        	_ecrans[_ecran_actif.val].onEvent(event);
	    }
	}
	
	
	
	protected void update()
	{
		Time now = _clock.getElapsedTime();   
		Time elapsed = Time.sub(now, _timeBefore);  
		_timeBefore = now;   
		
		_background.update(elapsed);   
		
		_ecrans[_ecran_actif.val].update(elapsed);  
		
		_debug.update(elapsed);   
		
		
	}
	
	
	protected void draw()
	{
		_window.clear(Color.WHITE);
		
		_window.draw(_background);
		
		_window.draw(_ecrans[_ecran_actif.val]);
		
		_window.draw(_debug);
		
		_window.display();
	}
	
}