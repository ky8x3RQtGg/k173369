
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class GUI extends JFrame{

	JFrame frame = new JFrame();

	GUI(){

	}

	/*実際の登録画面用の関数*/
	public void registration_panel(final StudentCard sc) {

		setTitle(sc.getStudentName() + "の情報登録");
		setBounds(100,100,500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		JLabel labeltext = new JLabel("200*200の画像をどうぞ");
		final JLabel iconLabel = new JLabel(sc.getIcon());
		iconLabel.setPreferredSize(new Dimension(200,200));

		JLabel label = new JLabel("自由テキストです");

		final JTextArea text = new JTextArea(5,30);
		text.setLineWrap(true);


		JButton button1 = new JButton("画像参照");
		JButton button2 = new JButton("情報登録");

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("画像参照");

				//GUIからファイルを開く
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int choice = fileChooser.showOpenDialog(frame);
				if(choice == JFileChooser.APPROVE_OPTION) {

					File file = fileChooser.getSelectedFile();
					
					ImageIcon icon = new ImageIcon(file.getPath());
					iconLabel.setIcon(icon);
					sc.setIcon(icon);
				}

			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.setFree_txt(text.getText());//テキスト情報をフィールドへセット

				System.out.println(sc.getStudentName()+"の自由テキスト");
				System.out.println(sc.getFree_txt());
				System.out.println();

				/*終了*/
				Component c = (Component)e.getSource();
				Window w= SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}

		});

		p1.add(labeltext);
		p1.add(iconLabel);
		p1.add(button1);


		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(label);
		p2.add(text);

		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(button2);

		getContentPane().add(p1,BorderLayout.PAGE_START);
		getContentPane().add(p2,BorderLayout.WEST);
		getContentPane().add(p3,BorderLayout.PAGE_END);

		setVisible(true);
	}

}
