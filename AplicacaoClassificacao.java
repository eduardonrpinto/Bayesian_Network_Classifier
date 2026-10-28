package Learning.Amostra.Amostra.Amostra;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;




public class AplicacaoClassificacao extends JFrame{
	
	private JFrame frame;
	private JTextField Vetor;
	private RN rede;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoClassificacao window = new AplicacaoClassificacao();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AplicacaoClassificacao() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnReadRN = new JButton("Ler Rede de Bayes");
		btnReadRN.setBounds(159, 21, 117, 29);
		frame.getContentPane().add(btnReadRN);
		
    	btnReadRN.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnReadRN);
				File f = fc.getSelectedFile();
				String path = f.getAbsolutePath();
				rede = RN.read(path);
			}
		});
		
		
		JButton btnClass = new JButton("Classificar");
		btnClass.setBounds(159, 21, 117, 29);
		frame.getContentPane().add(btnClass);
		
		btnClass.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		String[] linhas = Vetor.getText().split(",");
        int[] array = new int[linhas.length];

        for (int i = 0; i < linhas.length; i++) {
            array[i] = Integer.parseInt(linhas[i].trim());
        }
        
        
        	if(array.length==rede.getNumvar()-1) {
			Classificador classificador = new Classificador();
	        int resultadoClassificacao =0;
	        resultadoClassificacao = classificador.classificador(rede, array);
	        JOptionPane.showMessageDialog(frame, "Resultado da Classificação: " + resultadoClassificacao);
		}else{
			JOptionPane.showMessageDialog(frame,"Vetor com dimensão errada");
		}
         
        
        
         
         
		
		}
	    });

		
		
		Vetor = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vetor"));
        panel.add(Vetor);
        panel.add(btnReadRN);
        panel.add(btnClass);
        
        // Configurar frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setContentPane(panel);
        this.setVisible(true);
		
		
		
		
		
		
		
	}

}
