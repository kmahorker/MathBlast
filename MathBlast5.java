/*Game*/

//imports
import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MathBlast5 extends JFrame{//Create Frame
        CardLayout cards;//Other Global Vars
        Pan p;
        IntroPanel ip;//create Panels
        InstructionsPanel ipp;
        GamePanel gp;
        TutorialPanel tp;
        Image bg;
        int lvl=1;
        String type  ="add";
        int num1=12;
		int num2=15;
		int realAns=27;
        int[]loc = {70, 270,470,670};
        public static void main(String[]args){//Main
                MathBlast5 mb = new MathBlast5();//Instantiate constructor
        }
        public MathBlast5(){//Frame Specifics
                super("Math Blast!");
                p= new Pan();
                setContentPane(p);
                setSize(800,800);//size: 800x800
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setLocation(10,50);
                setResizable(false);
                setVisible(true);
                bg = Toolkit.getDefaultToolkit().getImage("spacebg.jpg");//Set background to imported image of cool space BG

}

class Pan extends JPanel{//Panel Class


        Pan(){//Constructor
                cards = new CardLayout();
                setLayout(cards);//Card Layout
                //Create 4 card Panels
                ip = new IntroPanel();//Intro Panel
                //ip.setBackground(Color.BLUE);
                ipp = new InstructionsPanel();//Instructions Panel
                ipp.setBackground(Color.RED);
                gp = new GamePanel();//Game Panel
                gp.setBackground(Color.PINK);
                tp = new TutorialPanel();//Tutorial Panel
                tp.setBackground(Color.GREEN);
                add(ip, "intro");//add panels
                add(ipp, "instruct");
                add(gp, "game");
                add(tp, "tut");

        }

        public void paintComponent(Graphics g){
                super.paintComponent(g);
        }
}

class IntroPanel extends JPanel implements ActionListener{//IntroPanel >ActionListener
        JButton start;//Start Button
        JButton inst;//Instructions Button

        IntroPanel(){//Constructor
                setLayout(new FlowLayout());
                start = new JButton("Start the Mission!");//Add and make Buttons
                inst = new JButton("Your Mission Instructions");
                start.addActionListener(this);
                inst.addActionListener(this);
                add(start);
                add(inst);

        }

        public void actionPerformed(ActionEvent e){//Event Handler method
                if(e.getSource()==start){//If Start Button Pressed
                        cards.show(p,"game");//Show Game Panel
                }
                else if(e.getSource()==inst){//Else if Instructions Button Pressed
                        cards.show(p,"instruct");//Show Instructions Panel
                }
        }
        public void paintComponent(Graphics g){//PaintComponent
                super.paintComponent(g);//Super
                g.drawImage(bg,0,0,800,800,this);
        }
}
class InstructionsPanel extends JPanel implements ActionListener{//Instructions Panel > Action Listenerm implementsActionListener
        //JLabel
        JButton back;//Back Button
        Image i;
        Image i2;
        Image i3;
        //Image bg1;
        InstructionsPanel(){//Constructor
                back= new JButton("Back");//Create Back Button
                back.addActionListener(this);//Add
                add(back);
                i= Toolkit.getDefaultToolkit().getImage("leftarrow.jpg");
                i2 = Toolkit.getDefaultToolkit().getImage("rightarrow.jpg");
                i3 = Toolkit.getDefaultToolkit().getImage("spacebar.jpg");
        }
        public void actionPerformed(ActionEvent e){//Evt Handler
                if(e.getSource()==back){//If Back clicked
                        cards.show(p,"intro");//Show intro panel
                }
        }

        public void paintComponent(Graphics g){//PaintComponent
                super.paintComponent(g);
                g.drawImage(bg,0,0,800,800,this);
                System.out.println("ok");
                g.setColor(Color.WHITE);
                g.setFont(new Font("SansSerif",Font.BOLD,25));
                g.drawString("Instructions",315,70);
                //g.setFont(new Font("SansSerif",Font.PLAIN,15));
                g.setFont(new Font("SansSerif",Font.BOLD,18));
                g.drawString("Objective: Shoot the correct answer and get the highest score.",80,100);
                g.drawString("Controls:",80,140);
                g.drawString("Use the left and right arrow keys to control the spaceship:",80,180);
                g.drawImage(i,300,220,this);
                g.drawImage(i2,425,220,this);
                g.drawString("Click the spacebar to shoot the laser:",80,320);
                g.drawImage(i3,130,350,this);
                g.drawString("Click the Tutorial button if you need help solving a problem:",80,460);



        }
}
class GamePanel extends JPanel implements KeyListener, ActionListener,FocusListener{//Game Panel >KeyListener,ActionListener,FocusListener
        JButton tut;//Variables
        Image ship;
        Image laser;
        JMenuBar mb;
        JMenu l;
        JMenu a;
        JMenu s;
        JMenu m;
        JMenu d;
        JMenuItem ae;
        JMenuItem am;
        JMenuItem ah;
        JMenuItem se;
        JMenuItem sm;
        JMenuItem sh;
        JMenuItem me;
        JMenuItem mm;
        JMenuItem mh;
        JMenuItem de;
        JMenuItem dm;
        JMenuItem dh;
        JButton back;
		int shipx;
		int laserx;
		int lasery;
		String lvlsign = "+";
		boolean space;
		int count = 0;
		boolean newNum = true;
		int realAnsX;
		int realAnsArr;
		int randAns1=12;
		int randAnsX1;
		int randAnsArr1;
		int randAns2=13;
		int randAnsX2;
		int randAnsArr2;
		int randAns3=14;
		int randAnsX3;
		int randAnsArr3;
		boolean hit = false;
		int score = 0; //Score variable
		boolean changeAns;
		boolean right;
		JButton nextq;
		
        GamePanel(){//Constructor
        		shipx = 100;
        		
					tut = new JButton("Tutorial");//Create Explanation Button
					add(tut);
					tut.addActionListener(this);
                back = new JButton("Back");
                add(back);
                ship = Toolkit.getDefaultToolkit().getImage("spaceship.png");//Import image of spaceship
                laser = Toolkit.getDefaultToolkit().getImage("laser.png");
                //Import space Background and Set Background
                //Import Laser beam image
                //MediaTracker
                //???Create JLabel for Score Label (NOT ACTUAL SCORE)
                mb = new JMenuBar();//JMenuBar\
                add(mb);
                l = new JMenu("Levels");//JMenu
                mb.add(l);
                a = new JMenu("Addition");//JMenu\
                l.add(a);
                s = new JMenu("Subtraction");//JMenu
                l.add(s);
                m = new JMenu("Multiplication");//JMenu
                l.add(m);
                d = new JMenu("Division");//JMenu
                l.add(d);
                //JMenuItems {Multiplication,Division,Addition,Subtraction}
                ae = new JMenuItem("Easy");
                a.add(ae);
                am = new JMenuItem("Medium");
                a.add(am);
                ah = new JMenuItem("Hard");
                a.add(ah);
                me = new JMenuItem("Easy");
                m.add(me);
                mm = new JMenuItem("Medium");
                m.add(mm);
                mh = new JMenuItem("Hard");
                m.add(mh);
                se = new JMenuItem("Easy");
                s.add(se);
                sm = new JMenuItem("Medium");
                s.add(sm);
                sh = new JMenuItem("Hard");
                s.add(sh);
                de = new JMenuItem("Easy");
                d.add(de);
                dm = new JMenuItem("Medium");
                d.add(dm);
                dh = new JMenuItem("Hard");
                d.add(dh);
                addKeyListener(this);                
                back.addActionListener(this);
                ae.addActionListener(this);
                am.addActionListener(this);
                ah.addActionListener(this);
                se.addActionListener(this);
                sm.addActionListener(this);
                sh.addActionListener(this);
                me.addActionListener(this);
                mm.addActionListener(this);
                mh.addActionListener(this);
                de.addActionListener(this);
                dm.addActionListener(this);
                dh.addActionListener(this);
                addFocusListener(this);
				
                tut.setEnabled(true);
        }

        public void focusGained(FocusEvent f){}//Evt Handlers (2) Focus Event
        public void focusLost(FocusEvent f){}
        public void keyTyped(KeyEvent e){}
        public void keyReleased(KeyEvent e){}
        public void keyPressed(KeyEvent e){//Key Pressed EVT Handler
			requestFocus();//requestFocus
            int k = e.getKeyCode();
            if(k==(e.VK_LEFT)){//if left arrow key
				if(shipx>15){
					shipx= shipx-30;//Ximage-=5
					newNum=false;
				}
			}
			else if(k==(e.VK_RIGHT)){//if right arrow key
				if(shipx<640){
					shipx = shipx+30;//Ximage+=5
					newNum=false;
				}
			}
			if(k==(e.VK_SPACE)){//if space
				laserx= shipx+56;//At Current Position set Laser coords to current + 20??? (Center of spaceship)
				lasery = 400;
				space=true;
				newNum=false;
				hit = checkIfHit(laserx,realAnsX,lasery);
				if(hit==true){
					score++;
					//repaint(0,0,800,210);
					changeAns=true;
					System.out.println("Correct123123");
					hit=false;
					right=true;
				}
			else{
				score--;
				changeAns=false;
				right=false;
			}
                }
                repaint();//repaint whole panel
        }
        
        public void actionPerformed(ActionEvent e){//ActionPerformed evt handler
                if (e.getSource()==tut){//if Explanation button clicked
                        cards.show(p,"tut");//show explanation Panel
                }
                if(e.getSource()==back){
                        cards.show(p,"intro");
                }

                /*Make boolean true when certain Menu item is picked*/
                        //e.g. if multiplication is clicked
                               //mult boolean = true
						if(e.getSource()==ae){
							lvlsign= "+";
							lvl = 1;
							newNum=true;
							type = "add";
							tut.setEnabled(true);
							
						}
						else if(e.getSource()==am){
							lvlsign= "+";
							lvl = 2;
							newNum=true;
							type = "add";
							tut.setEnabled(true);
						}
						else if(e.getSource()==ah){
							lvlsign= "+";
							lvl = 3;
							newNum=true;	
							type = "add";
							tut.setEnabled(true);
						}
						else if(e.getSource()==se){
							lvlsign= "-";
							lvl = 1;
							newNum=true;
							type = "sub";
							tut.setEnabled(false);
							
						}
						else if(e.getSource()==sm){
							lvlsign= "-";
							lvl = 2;
							newNum=true;
							type = "sub";
							tut.setEnabled(false);
						}
						else if(e.getSource()==sh){
							lvlsign= "-";
							lvl = 3;
							newNum=true;
							type = "sub";	
							tut.setEnabled(false);
						}
						else if(e.getSource()==me){
							lvlsign= "x";
							lvl = 1;
							newNum=true;
							type = "mult";
							tut.setEnabled(false);
							
						}
						else if(e.getSource()==mm){
							lvlsign= "x";
							lvl = 2;
							newNum=true;
							type = "mult";
							tut.setEnabled(false);
						}
						else if(e.getSource()==mh){
							lvlsign= "x";
							lvl = 3;
							newNum=true;	
							type = "mult";
							tut.setEnabled(false);
						}
						else if(e.getSource()==de){
							lvlsign= "/";
							lvl = 1;
							newNum=true;
							type = "div";
							tut.setEnabled(false);
						}
						else if(e.getSource()==dm){
							lvlsign= "/";
							lvl = 2;
							newNum=true;
							type = "div";
							tut.setEnabled(false);
						}
						else if(e.getSource()==dh){
							lvlsign= "/";
							lvl = 3;
							newNum=true;	
							type = "div";
							tut.setEnabled(false);
						}
				 repaint();//repaint
        }
                
        public void paintComponent(Graphics g){ //PaintComp.
			super.paintComponent(g);
			requestFocus();
			g.drawImage(bg,0,0,800,800,this);
			g.drawImage(ship,shipx,550,142,200,this);//Draw SpaceShip
			if(lasery>=50){
				g.drawImage(laser,(laserx),lasery,this); //Draw Laser
				lasery = lasery-100;
				if((lasery==100)&&(changeAns=true)){
					newNum=true;
					changeAns=false;
				}	
				pause();//call pause
			}
			g.setColor(Color.BLACK);
			g.fillRect(245,50,300,100);//Draw Rectangle at Top
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif",Font.BOLD,25));
			if(newNum==true){
				if((type=="div")||(type=="mult")){
					num1 = DivRand(lvl);
					num2 = DivRand(lvl);
					if(type=="div"){
						num1 = Div(num1,num2);
					}
				}
				else{
					num1 = Random(lvl);//Call Random Gen twice
					num2 = Random(lvl);
				}
				if(type=="sub"){
					while(num2>num1){
						num1 = Random(lvl);
						num2 = Random(lvl);
					}
				}
					
			/*Creating Real Ans*/
					///Problem: How to put the REAL answer in a different location each time?
						//Soln: Array of fixed random coords, choose one randome one each time, and make sure other ans are not in same coords
					realAns = createAns(type,num1,num2);//Call Create Ans
					realAnsArr = ((int)(Math.random()*4+0));
					realAnsX = loc[realAnsArr];
			/*Creating Random Answer Choices*/
					randAns1 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
					randAnsArr1 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
					while(randAnsArr1==realAnsArr){
					   randAnsArr1 = ((int)(Math.random()*4+0));
					}
					randAnsX1= loc[randAnsArr1];        
					while((randAns1==realAns)){	
							randAns1 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
							randAnsArr1 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
							while(randAnsArr1==realAnsArr){
								randAnsArr1 = ((int)(Math.random()*4+0));
							}
					  randAnsX1= loc[randAnsArr1];  
					}
					randAns2 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
					randAnsArr2 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
					while((randAnsArr2==realAnsArr)||(randAnsArr2==randAnsArr1)){
					   randAnsArr2 = ((int)(Math.random()*4+0));
					}
					randAnsX2= loc[randAnsArr2];        
					while(((randAns2==realAns)||(randAns2==randAns1))){	
							randAns2 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
							randAnsArr2 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
							while((randAnsArr2==realAnsArr)||(randAnsArr2==randAnsArr1)){
								randAnsArr2 = ((int)(Math.random()*4+0));
							}
					  randAnsX2= loc[randAnsArr2];  
					}
					randAns3 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
					randAnsArr3 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
					while((randAnsArr3==realAnsArr)||(randAnsArr3==randAnsArr2)||(randAnsArr3==randAnsArr1)){
					   randAnsArr3 = ((int)(Math.random()*4+0));
					}
					randAnsX3= loc[randAnsArr3];        
					while(((randAns3==realAns)||(randAns3==randAns1)||(randAns3==randAns2))){	
							randAns3 = randAns(type,num1,num2,lvl);   //Call Create answer and send 2 random nums
							randAnsArr3 = ((int)(Math.random()*4+0)); //draw Ans at Random Xcoord Location
							while((randAnsArr3==realAnsArr)||(randAnsArr3==randAnsArr2)||(randAnsArr3==randAnsArr1)){
								randAnsArr3 = ((int)(Math.random()*4+0));
							}
					  randAnsX3= loc[randAnsArr3];  
					}
				if(right==true){
					g.setColor(Color.GREEN);
					 g.setFont(new Font("SansSerif",Font.BOLD,55));
					g.drawString("CORRECT!",270,300);
					//pause();
					right= false;
				}
				else if(right==false){
					g.setColor(Color.RED);
					 g.setFont(new Font("SansSerif",Font.BOLD,55));
					g.drawString("TRY AGAIN!",250,300);
					pause();
					right= false;
				}
					newNum = false;		
			}
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif",Font.BOLD,25));
			g.drawString(""+ realAns, realAnsX,185);//drawString Real Answer
			g.drawString(""+ randAns1, randAnsX1,185);
			g.drawString(""+ randAns2, randAnsX2,185);
			g.drawString(""+ randAns3, randAnsX3,185);
			System.out.println(num1 + " " + num2);
			g.drawString(" "+ num1 + " " + lvlsign + " " + num2 + "  =  ?" ,280,110);//Draw the 2 random ints and a sign {*,/,+,-} between and then "=  ?"
			g.drawString("Score: " + ""+ score, 15,40);
		}
        public void pause(){//Pause Method
			try{//try
			Thread.sleep(100);//Sleep for 100 ms
			}catch(InterruptedException ex){}//catch exception
		  gp.repaint(); //repaint
        }
        public int Div(int num1, int num2){
			int m = num1;
			int d = num2;
			int n = 0;
			n = num1*num2;
			return n;
		}
		public int DivRand(int lvl){
			int l = lvl;
			int ans = 0;
			if(l==1){
				ans = ((int)(Math.random()*12+1));
			}
			if(l==2){
				ans = ((int)(Math.random()*30+12));
			}
			if(l==3){
				ans = ((int)(Math.random()*100+30));
			}
			return ans;
		}
		
        public int Random(int lvl){//Random Generator Method (Accepts Level #and Returns 1 int)
			int k = lvl;//Check level...
			int coord = 0;
			if(k==1)//If Level==1
					coord =((int)(Math.random()*99	+1));//Generate int btw 0 and 10
					
			if(k==2)//If Level 2
					coord = ((int)(Math.random()*899+100));//int 10-100
			if (k==3)//If Level 3
					coord = ((int)(Math.random()*8999+1000));//int 100-1000
			System.out.println("k: " + ""+ k+ "coord: "+ ""+ coord);
			
			return coord;//Return
        }
        public int createAns(String t, int f, int k){//Create Answer Method Accept 2 varsand return ans
			int realAns = 0;
			int num1 = f;
			int num2 = k;
			String type = t;
				 if(type=="mult")   //if mult
						  realAns = num1*num2;  //mult 2 vars
				 if(type=="div")//if div
							realAns = num1/num2;//div 2 vars
				 if(type=="add")//if add
							realAns = num1+num2;//add 2 vars
				 if(type=="sub")  //if sub
							realAns = num1-num2;//sub 2 vars
							
			return realAns;          //return
        }
        public int randAns(String t, int f, int k, int l){
			int randAns = 0;
			int num1 = f;
			int num2 = k;
			int lvl = l;
			String type = t;
			int b = 0;
			
				do{
					b = ((int)(Math.random()*2+1));
					if(b==1){
						num1 = num1+((int)(Math.random()*10+1));
						num2 = num2 +((int)(Math.random()*10+1));
					}
					if(b==2){
						num1 = num1-((int)(Math.random()*10+1));
						num2 = num2 -((int)(Math.random()*10+1));
					}
				}while((num1<=0)||(num2<=0));

			if(type=="add"){
				randAns = num1+num2;
			}
			else if(type=="mult"){
				randAns = num1*num2;
			}
			else if(type=="sub"){
				randAns = num1-num2;
				
			}
			else if(type=="div"){
				randAns = num1/num2;
			}
			
			return randAns;
		}
        public boolean checkIfHit(int laserx, int realAnsX, int lasery){//CheckIfHit Method:Accepts Laser x coord and Real ans X coord
                if((laserx>=(realAnsX-40))&&(laserx<=(realAnsX+40))){//if Laser X Coord is within 10 of REAL Ans X Coord
                      System.out.println("Correct");
                        return true;//return true
				}
                        //score++
                else{//else
                     System.out.println("nope");   //false
				return false;
				}
        }       
}

class TutorialPanel extends JPanel implements ActionListener{//TutorialPanel
        JButton bc;//Button
        TutorialPanel(){//Constructor
                bc = new JButton("Back");//Create "Back" Button
				bc.addActionListener(this);
                add(bc);
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==bc){
				cards.show(p,"game");
			}
		}
        ///Consult Adviser on how to teach the program
        public void paintComponent(Graphics g){
			super.paintComponent(g);
			int n1 = num1;
			int n2 = num2;
			int n1d, n2d;
			int pos = 1;
			int sum = 0; 
			int prevRegroup = 0;
			int regroup = 0;
			int n1Len = String.valueOf(n1).length();
			int n2Len = String.valueOf(n2).length();
			int maxLen;
			
			if (n1Len > n2Len) {
				maxLen = n1Len;
			} else {
				maxLen = n2Len;
			}
			
			g.drawImage(bg,0,0,800,800,this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("SansSerif",Font.BOLD,25));
			g.drawString("Example:",50,80);
			System.out.println("n1Len: " + n1Len);
			System.out.println("n2Len: " + n2Len);
			g.drawString(""+num1, 300 + 15*(maxLen-n1Len), 100);
			g.drawString("+"+num2,285 + 15*(maxLen-n2Len),130);
			g.drawLine(290,138,340,138);
			
			while(n1 >= 1 || n2 >= 1){
				n1d = n1%10;
				n2d = n2%10;
				sum = n1d + n2d + prevRegroup;
				regroup = sum/10;
								
				switch(pos){
					case 1: g.setColor(Color.GREEN); g.drawString("Add up the one's digits: "+ n1d + "+" + n2d + "=" +  sum ,20,120+100*pos);
							g.drawString((sum%10)+"",300 + 15*(maxLen-pos),160);											
							break;
					
					case 2: g.setColor(Color.RED); g.drawString("Add up the tens digits:" + n1d + "+" + n2d + "+" + prevRegroup + "=" + sum,20,120+100*pos);
							g.drawString(""+ (sum%10), 300 + 15*(maxLen-pos),160);
							break;
							
					case 3: g.setColor(Color.YELLOW); g.drawString("Add up the hundreds digits:" + n1d + "+" + n2d + "+" + prevRegroup + "=" + sum,20,120+100*pos);
							g.drawString(""+ (sum%10),300 + 15*(maxLen-pos),160);
							break;
							
					case 4: g.setColor(Color.ORANGE); g.drawString("Add up the thousands digits:" + n1d + "+" + n2d + "+" + prevRegroup + "=" + sum,20,120+100*pos);
							g.drawString(""+ (sum%10),300 + 15*(maxLen-pos),160);
							break;
							
					default: break;
				}
				
				if((regroup>0) && (pos<maxLen)){
						g.drawString("(Make sure you regroup!)",490,120+100*pos);
						g.drawString(regroup+"", 285 + 15*(maxLen-pos),75);
				}
				
				prevRegroup = regroup;
				n1 /= 10;
				n2 /= 10;
				
				pos++;
			}
			
			if (sum/10 >= 1) {
					g.drawString(""+ (sum/10),285,160);
			}
			
			g.setColor(Color.WHITE);	
			g.drawString("Now shoot the answer: " + realAns,20,600);
				
		}
	}
}
