import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JColorChooser;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.Color;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class Editorial  implements ActionListener,WindowListener
{
    JMenuItem neww,save,saveas,open,cut,copy,paste,font,fontcolor,backgroundcolor;
     JTextArea jt;
     JMenu jmenu,edit,format;
     JFrame jf,foframe;
     File f;
     JComboBox choosefont,size,fontstyle;
     JButton jb;
  Editorial()
  {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    jf=new JFrame("Untitled-Notepad");
     jf.setSize(700,500);
    jf.setLocationRelativeTo(null);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
     jt=new JTextArea("");
    JMenuBar jbar=new JMenuBar();
    jmenu=new JMenu("file");
    edit=new JMenu("Edit");
    format=new JMenu("format");
    
    neww=new JMenuItem("new");
    neww.addActionListener(this);
    save=new JMenuItem("save");
    save.addActionListener(this);
    saveas=new JMenuItem("saveas");
    saveas.addActionListener(this);
    open=new JMenuItem("open");
    open.addActionListener(this);
    cut=new JMenuItem("Cut");
    cut.addActionListener(this);
    edit.add(cut);
    paste=new JMenuItem("paste");
    paste.addActionListener(this);
    edit.add(paste);
    copy=new JMenuItem("Copy");
    copy.addActionListener(this);
    edit.add(copy);
    font=new JMenuItem("font");
    font.addActionListener(this);
    format.add(font);
    fontcolor=new JMenuItem("FONT COLOR");
    format.add(fontcolor);
    fontcolor.addActionListener(this);
    backgroundcolor=new JMenuItem("BACKGROUND COLOR");
    format.add(backgroundcolor);
    backgroundcolor.addActionListener(this);
    
    jmenu.add(open);
    jmenu.add(save);
    jmenu.add(saveas);
    jmenu.add(neww);
    jbar.add(jmenu);
    jbar.add(edit);
    jbar.add(format);
    jf.setJMenuBar(jbar);
    jf.add(jt);
    JScrollPane jpane=new JScrollPane(jt);
    jpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jf.add(jpane);
    
    jf.setVisible(true);
  }
  public static void main(String[] args)
  {
    new Editorial();  
  }
  public void actionPerformed(ActionEvent e)
  {
     if(e.getSource()==neww ) 
      {
         jt.setText("");
      }   
     if(e.getSource()==saveas)
      { 
         saveas();
      }   
    
     if(e.getSource()==save)
      {
         save();
      }  
     if(e.getSource()==open)
      {
         openfile();
      }
     if(e.getSource()==cut)
      {
         jt.cut();
      } 
     if(e.getSource()==paste)
      {
         jt.paste();
      }  
     if(e.getSource()==copy)
      {
         jt.copy();
      }
     if(e.getSource()==font)
     {
         openfontframe();
     }
     if(e.getSource()==jb)
     {
         setokbutton();
     }
     if(e.getSource()==fontcolor)
     {
         Color c= JColorChooser.showDialog(jf, "choose color", Color.BLACK);
         jt.setForeground(c);
     } 
     if(e.getSource()==backgroundcolor)
     {
         Color c=JColorChooser.showDialog(jf, "choose color", Color.WHITE);
         jt.setBackground(c);
     }
   }
  void setokbutton()
  {
      String font_family=(String)choosefont.getSelectedItem();
        String fontsizes=(String)size.getSelectedItem();        //10,20,30
        String stylee=(String)fontstyle.getSelectedItem();      //plain, bold, 
        
        int fontstylee=0;
        if(fontstyle.equals("plain"))
        {
            fontstylee=0;
        }
        else if(fontstyle.equals("bold"))
        {
            fontstylee=1;
        }
        else if(fontstyle.equals("italic"))
        {
            fontstylee=2;
        }
        Font fontt=new Font(font_family, fontstylee, Integer.parseInt(fontsizes));
        jt.setFont(fontt);
         foframe.setVisible(false);
  }        
void openfontframe()
{
    foframe=new JFrame("font");
    foframe.setSize(500,300);
    foframe.setLocationRelativeTo(jf);
    foframe.setLayout(null);
   
    
    String[] fontfamily=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    choosefont=new JComboBox(fontfamily);
    choosefont.setBounds(50,30 ,100, 60);
    foframe.add(choosefont);
    
    String[] sizes={"10","12","16","22","26","32","40","48","68","80"};
    size=new JComboBox(sizes);
    size.setBounds(200,30 ,100, 60);
    foframe.add(size);
    
    
    String[] style={"plain", "bold", "italic"};
    fontstyle=new JComboBox(style);
    fontstyle.setBounds(350,30 ,100, 60);
    foframe.add(fontstyle);
    
    jb=new JButton("ok");
    jb.setBounds(200, 120, 80, 40);
    jb.addActionListener(this);
    foframe.add(jb);
 
    
    foframe.setVisible(true);
    
}
  
  void openfile()
  {
    JFileChooser jfil=new JFileChooser();
       int n=jfil.showOpenDialog(jf);
       if(n==0)
       {
          jt.setText("");
           f =jfil.getSelectedFile();
           jf.setTitle(f.getName());
           try(FileInputStream fi=new FileInputStream(f);)
           {
               int i;
               while((i=fi.read()) !=-1)
               {
               jt.append(String.valueOf((char)i));
               }    
           } 
           catch(IOException ne)
           {
               System.out.println(ne);
           }      
        }
    }
  void saveas()
  {
     JFileChooser jfil=new JFileChooser();
     int result=jfil.showSaveDialog(save);
     if(result==0)
       { 
         String text=jt.getText();
         File f=jfil.getSelectedFile();
         try(FileOutputStream filo=new FileOutputStream(f))
           {
                byte[] b=text.getBytes();
                filo.write(b);
            }
            catch(IOException ee)
            {
                ee.printStackTrace();
            }  
       }       
   }
   void save()
   {
        String title= jf.getTitle();
       if(!title.equals("Untitled-Notepad") )
       {
         saveas();       
       }
      else
       {
         String text=jt.getText();
         try(FileOutputStream fu=new FileOutputStream("abc.txt"))
         {
          byte[]b=text.getBytes();
          fu.write(b);
         }
        catch(Exception ae)
        {
            System.out.println(ae);        
        }   
       }
   } 
     @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("111111");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("33333");
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("44444");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("555555");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("6666666");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("777777");
    }
}
