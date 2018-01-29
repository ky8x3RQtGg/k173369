package ICCharger;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class StudentCard {

    private Integer idNum;
    private String name;
    private Integer accountBalance;
    private BufferedImage face = null;
    private String text;
    private ImageIcon icon;

    public Integer getIdNum() {
	return idNum;
    }

    public String getName() {
	return name;
    }

    public Integer getAccountBalance() {
	return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
	this.accountBalance = accountBalance;
    }

    public String getText() {
	return text;
    }

    public BufferedImage getFace() {
	return face;
    }

    public StudentCard(){
	this(Integer.valueOf(-1), "", Integer.valueOf(0), null, "");
    }

    public StudentCard(Integer idNum, String name){
	this(idNum, name, Integer.valueOf(0), null, "");
    }

    public StudentCard(Integer idNum, String name, Integer amount){
	this(idNum, name, Integer.valueOf(0), null, "");
    }

    public StudentCard(Integer idNum, String name, Integer amount, File faceFile, String text){
	this.idNum = idNum;
	this.name = name;
	this.accountBalance = amount;
	try {
	    this.face = ImageIO.read(faceFile);
	} catch (Exception ex) {
	    System.err.println("Exception caused: " + ex.getMessage());
	}
	this.text = text;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public void settext(String text) {
		this.text = text;
	}
	public String gettext() {
		return text;
	}

}
