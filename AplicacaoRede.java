package Learning.Amostra.Amostra.Amostra;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.io.File;



public class AplicacaoRede extends JFrame {
	
	private JFrame frame;
	private Amostra am;
	private JTextField Grafos;
	private JTextField S;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoRede window = new AplicacaoRede();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AplicacaoRede() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLoadCSV = new JButton("Ler Amostra");
		btnLoadCSV.setBounds(159, 21, 117, 29);
		frame.getContentPane().add(btnLoadCSV);
		
		btnLoadCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnLoadCSV);
				File f = fc.getSelectedFile();
				String path = f.getAbsolutePath();
				String name = f.getName();
				am = new Amostra(path);
				
				System.out.println(am);
			}
		});
		
		
		
		JButton executarButton = new JButton("Executar Algoritmo");
        executarButton.setBounds(159, 21, 117, 29);
		frame.getContentPane().add(executarButton);
		
		executarButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
        int parametro2 = Integer.parseInt(Grafos.getText());
        double parametro3 = Double.parseDouble(S.getText());


        // Criar uma instância do algoritmo e chamar o método algoritmo
        Algoritmo algoritmo = new Algoritmo();
        RN redetreinada = algoritmo.algoritmo(am, parametro2, parametro3);
        
        
    	JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(executarButton);
		String path = fc.getSelectedFile().getAbsolutePath();
		System.out.println(path);
		redetreinada.write(path);
		System.out.println(path);
		redetreinada.write(path);
        
		}
		
		});
		
		
		
		
		
        Grafos = new JTextField(10);
        S = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Número de grafos"));
        panel.add(Grafos);
        panel.add(new JLabel("S:"));
        panel.add(S);
        panel.add(btnLoadCSV);
        panel.add(executarButton);
        
        // Configurar frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setContentPane(panel);
        this.setVisible(true);
        
        
        
        
        
		
	}
	
	
	
	
	
}
