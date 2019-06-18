import javax.swing.*;

public class Main{
    public static void main(String[]args)
    {
        System.out.println("Program Started!");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Cannot Load UI",
                    "Error 01",
                    JOptionPane.WARNING_MESSAGE);
        }
        AppObjects appObjects = new AppObjects();
        MainFrame mainFrame = new MainFrame(appObjects);
    }
}
