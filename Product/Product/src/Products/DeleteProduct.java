package Products;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DeleteProduct extends JFrame implements ActionListener {

	JTextField t1;
	JLabel t2, t3;
	JButton b1, b2, b3;
	RandomAccessFile raf;
	File fl = new File("data.txt");

	public DeleteProduct() {

		try {
			raf = new RandomAccessFile(fl, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		setBounds(400, 150, 830, 500);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		JLabel l0 = new JLabel("DELETE PRODUCT");
		l0.setBounds(120, 10, 300, 18);
		l0.setFont(new Font("Tahoma", Font.BOLD, 18));
		l0.setForeground(Color.RED);
		add(l0);

		JLabel l1 = new JLabel("Product ID:");
		l1.setBounds(30, 90, 150, 25);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(l1);

		t1 = new JTextField();
		t1.setBounds(220, 90, 200, 25);
		t1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(t1);

		b3 = new JButton("Search");
		b3.setForeground(Color.BLACK);
		b3.setBackground(Color.GRAY);
		b3.setBounds(425, 95, 80, 20);
		b3.addActionListener(this);
		add(b3);

		JLabel l2 = new JLabel("Product Name:");
		l2.setBounds(30, 140, 150, 25);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(l2);

		t3 = new JLabel();
		t3.setBounds(220, 140, 200, 25);
		t3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(t3);

		JLabel l3 = new JLabel("Product Price:");
		l3.setBounds(30, 190, 150, 25);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(l3);

		t2 = new JLabel();
		t2.setBounds(220, 190, 200, 25);
		t2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(t2);

		b1 = new JButton("Delete");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setBounds(70, 330, 150, 25);
		b1.addActionListener(this);
		add(b1);

		b2 = new JButton("Back");
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.BLACK);
		b2.setBounds(240, 330, 150, 25);
		b2.addActionListener(this);
		add(b2);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Products/icon/delete.png"));
		Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l4 = new JLabel(i3);
		l4.setBounds(500, 100, 250, 250);
		add(l4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b3) {
			String id = t1.getText();

			try {
				raf.seek(0);
				String data;
				while ((data = raf.readLine()) != null) {
					data.trim();
					String[] str = data.split(" ");
					if (str[0].equals(id)) {
						t3.setText(str[1]);
						t2.setText(str[2]);
					}
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == b1) {
			String id = t1.getText();
			String data, mdata = "";

			try {
				raf.seek(0);
				int fp = 0;
				while ((data = raf.readLine()) != null) {
					data.trim();
					String[] str = data.split(" ");
					if (str[0].equals(id)) {
						continue;
					}
					System.out.println(data.length());
					mdata = mdata + data + "\n";
					fp = fp + data.length() + 1;
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				raf.close();
				new RandomAccessFile("data.txt", "rw").setLength(0);
				RandomAccessFile raf2 = new RandomAccessFile(fl, "rw");
				raf2.writeBytes(mdata);
				raf2.close();
				raf = new RandomAccessFile(fl, "rw");
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new DeleteProduct().setVisible(true);
	}

}
