import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics; // klasa do grafiki
import java.util.ArrayList; 



public class BubblePanel extends JPanel {
	Random rand = new Random(); // zeby miec dostep do funkcji z klasy random tworzy sie obiekt
	ArrayList<Bubble> bubbleList; // w <> umieszcza sie typ obiektu w tym przypadku z klasy Bubble, dynamiczna struktura arrayList obiektów Bubble
	int size = 25; //domyslny rozmiar bąbelka
	public BubblePanel() {
		bubbleList = new ArrayList<Bubble>();
		setBackground(Color.BLACK);	
		//testBubbles(); //wywolujemy metode testBubbles z konstruktora BublePanel
		addMouseListener( new BubbleListener()); //bublePanel ma odbierac zdarzenia myszy i przesłyłac je do klasy buubleListener
		addMouseMotionListener( new BubbleListener() ); //bubblePanel ma odbierac przeciagniecia myszą
		addMouseWheelListener(new BubbleListener());
	}
	public void paintComponent(Graphics canvas)
	{
		super.paintComponent(canvas);//wywołujemy oryginalna metode z klasy JPanel 
		for(Bubble b : bubbleList) //dla każdego b w trukturze bubbleList
		{
			b.draw(canvas); //na kżdym bąbelku b bedzie wywołana metoda draw aby narysowac bąbelek		
		}

	}
	public void testBubbles() {
		for(int n=0; n<100; n++)
		{
			int x = rand.nextInt(600); //okno ma szer 600 wiec współrz losowe nie większe niz
			int y = rand.nextInt(400);
			int size = rand.nextInt(50);
			bubbleList.add(new Bubble(x,y,size)); //tworzymy nowy obiekt Bubble i dodajemy go do bubbleList typu ArrayList


		}
repaint(); //przerysowuje tło i wywołuje paintComponent()

	}

 
private class BubbleListener extends MouseAdapter
{
	public void mousePressed(MouseEvent e) {// przycusniecie myszy obiekt MouseEvent przechowuje współrzedne x y miejsca w którym nastąpiło kliknięcie
		// będzie mozna je wydobyc getX getY
		bubbleList.add(new Bubble (e.getX(),e.getY(),size));//utworzenie nowego obiektu Bubble (przekazanie danych do konstruktora Bubble)
		//i dodanie nowego obiektu do arrayList
		
		repaint(); //przerysowanie ekranu-zmodyfikowana bubbleList zostaje narysowana
	}
	
	public void mouseDragged(MouseEvent e) {//przeviagniecie myszy z przysnieciem obiekt MouseEvent przechowuje współrzedne x y miejsca w którym nastąpiło kliknięcie
		// będzie mozna je wydobyc getX getY
		bubbleList.add(new Bubble (e.getX(),e.getY(),size));//utworzenie nowego obiektu Bubble (przekazanie danych do konstruktora Bubble)
		//i dodanie nowego obiektu do arrayList
		
		repaint(); //przerysowanie ekranu-zmodyfikowana bubbleList zostaje narysowana
	}
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if(System.getProperty("os.name").startsWith("Mac"))//kółko myszy inaczej zachowuje sie w macu a inaczej w win czy linuksie
		size+=e.getUnitsToScroll(); //pobieramy liczbe jednostek przeciagniecia koła i przekazujemy do size
		else
		size-=e.getUnitsToScroll(); 
		
	}
	
	
	
}

class Bubble  // klasy wewn zwykle sa private bo tak bezpieczniej, klasa Bubble bedzie
//dostepna tylko w klasie bubble panel to jest ENKAPSULACJA -UKRYWANIE WNETRZA PRZED INNyMI KLASAMI


{
	private int x;
	private int y;    // argumenty sa prywantne, tylko klasa bubble moze zmienic ich wartosc
	private int size;
	private Color color; //jest klasa Color w bibliotece awt
	public Bubble (int newX, int newY, int newSize) //konstruktor/metoda, konstruktor jest public
	{x=newX; //wrócic do tego czemu tak
	y=newY;
	size = newSize;
	color = new Color (rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));} //nextInt 256 - losowa liczba od 0 do 255, bo generuje liczby mniejsze niz max
	public void draw(Graphics canvas) {//kanwa Graphics i nazwie canvas
		canvas.setColor(color); //obiekt canvas ustawienie koloru
		canvas.fillOval(x-size/2, y-size/2, size, size); // i kształtu-koło (xy górny lewy rog ramy, szer wys ramy
	}
}
}



