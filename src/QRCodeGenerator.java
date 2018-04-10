import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.*;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Random;
public class QRCodeGenerator {
   // private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    
    private static void checkPhoneScan() throws InterruptedException{
        
            
    }
    private static void createQRImage(File qrFile, String qrCodeText, int size,
			String fileType) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
                Random rnd = new Random();
                 Random rand = new Random();
                 int n = rand.nextInt(3) + 1;
        switch (n) {
            case 1:
                graphics.setColor(Color.BLUE);
                break;
            case 2:
                graphics.setColor(Color.RED);
                break;
            case 3:
                graphics.setColor(Color.GREEN);
                break;
            default:
                break;
        }
		

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}
    public static void main(String[] args) throws WriterException, IOException, InterruptedException {
      
        
                MainMenu mn = new MainMenu();
                String qrCodeText = MainMenu.motherserial;
		String filePath = "C:\\Users\\arcillaroldan65\\Documents\\NetBeansProjects\\GetComputerInfoWithUsb\\JD.png";
		int size = 400;
		String fileType = "png";
		File qrFile = new File(filePath);
		createQRImage(qrFile, qrCodeText, size, fileType);
		System.out.println("DONE");
                ShowQrCode cd = new ShowQrCode();
                cd.setVisible(true);
                
    }
}