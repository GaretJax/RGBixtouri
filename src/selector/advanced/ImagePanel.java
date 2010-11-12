package selector.advanced;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import selector.advanced.models.ImageModel;

public class ImagePanel extends JPanel {
    
    /** Generated serialVersionUID */
    private static final long serialVersionUID = -7837324600466554905L;
    
    private ImageModel model;
    private SelectionPanel selectionPanel;
    private JPanel placeholder = new ImagePlaceholder();
    
    public ImagePanel() {
        this.setLayout(new BorderLayout());
        
        this.add(this.placeholder, BorderLayout.CENTER);
        this.add(new ControlPanel(this), BorderLayout.SOUTH);
    }
    
    public void setImage(String path) throws IOException {
        this.setImage(ImageIO.read(new File(path)));
    }
    
    public void setImage(BufferedImage image) {
        this.model = new ImageModel(image);
        
        if (image == null) {
            this.selectionPanel = null;
            this.add(this.placeholder, BorderLayout.CENTER);
        } else {
            this.selectionPanel = new SelectionPanel(this.model);
            this.add(this.selectionPanel, BorderLayout.CENTER);
        }
    }
    
    public ImageModel getModel() {
        return this.model;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.model != null) {
            Graphics2D g2d = (Graphics2D) g;

            int s = 10;
            int h = this.getHeight(), w = this.getWidth();        

            g2d.setColor(new Color(204, 204, 204));

            for (int x = 0; x < w; x += s) {
                for (int y = (x % (2 * s)); y < h; y += 2 * s) {
                    g2d.fillRect(x, y, s, s);
                }
            }
            
            Rectangle b = this.model.getBounds(this.selectionPanel.getSize());
            g.drawImage(this.model.getImage(), b.x, b.y, b.width, b.height, null);
        }
    }
    
}
