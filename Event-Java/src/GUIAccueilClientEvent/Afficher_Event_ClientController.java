/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIAccueilClientEvent;

import Models.Event;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import services.Service_Event;
import services.Service_Map;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import services.Service_Categorie_Event;

/**
 * FXML Controller class
 *
 * @author kagha
 */
public class Afficher_Event_ClientController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ListView<String> event_list;
    @FXML
    private Button detail_id;
    @FXML
    private BorderPane border_id;
    @FXML
    private TextField lookup;
    ObservableList<Event> dataList = FXCollections.observableArrayList();
    static String s;
    @FXML
    private ImageView image;
    static BufferedImage img;
    @FXML
    private Label space;
    @FXML
    private Button detail_id1;

    public ObservableList getList() {
        return list;
    }

    public ListView<String> getEvent_list() {
        return event_list;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String selected = s;
        loadData();
        List<Event> e = new ArrayList<Event>();
        int i;
        for (i = 0; i < list.size(); i++) {
            e.add(new Event((String) list.get(i)));
            dataList.add(e.get(i));

        }
        AccueilClientController ac = new AccueilClientController();
    }

    public void initialize() {
        loadData();
    }

    public void loadData() {
        //String  selected =event_list.getSelectionModel().getSelectedItem(); 
        Service_Event sp = new Service_Event();
        list.removeAll(event_list);
        list.addAll(sp.afficher_nom());
        event_list.setItems(list);
    }

    @FXML
    private void Detail(ActionEvent event) {
        try {
            Service_Event sp = new Service_Event();
            Service_Map mp = new Service_Map();
            String selected = s;
            Afficher_Event_ClientController e = new Afficher_Event_ClientController();
            if (selected == null || selected.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Séléctionner un événement a détaillée");
                return;
            }
            //System.out.println(selected);
            list.removeAll(event_list);
            list.clear();
            list.addAll(sp.fetch(String.valueOf(sp.get_ID(s))));
            list.remove(0);
            list.addAll(mp.detail(String.valueOf(sp.get_ID(s))));
            list.remove(9);
            //list.remove(10);
            // Create the ListView for the fruits
            Service_Categorie_Event sv = new Service_Categorie_Event();
            int a = Integer.parseInt((String) list.get(8));
            list.set(8, "Catégorie Event :" + sv.get_Nom(a));
            System.out.println(sp.fetch(selected));
            event_list.getSelectionModel().clearSelection();
            event_list.setItems(list);
            event_list.setMouseTransparent(true);
            event_list.setFocusTraversable(false);
            System.out.println(list.get(9).toString().substring(list.get(9).toString().lastIndexOf(":") + 1).trim());
            System.out.println(list.get(10).toString().substring(list.get(10).toString().lastIndexOf(":") + 1).trim());
            String fr = new String(list.toString().getBytes(), "UTF-8");
            //QR code
            //System.out.println(fr);
            String qrCodeData = "https://maps.google.com/?q=" + list.get(9).toString().substring(list.get(9).toString().lastIndexOf(":") + 1).trim() + "," + list.get(10).toString().substring(list.get(10).toString().lastIndexOf(":") + 1).trim();
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = null;
            try {
                matrix = new MultiFormatWriter().encode(
                        new String(qrCodeData.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, 200, 200, hintMap);
            } catch (WriterException ex) {
                Logger.getLogger(Afficher_Event_ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedImage bitimage = MatrixToImageWriter.toBufferedImage(matrix);
            Image image = SwingFXUtils.toFXImage(bitimage, null);
            this.image.setImage(image);
            img = bitimage;
            System.out.println("QR Code image created successfully!");

        } catch (IOException ex) {
            Logger.getLogger(Afficher_Event_ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void LookUp(ActionEvent event) {
    }

    @FXML
    private void test(KeyEvent event) {
        //String  selected =getSelected();
        //System.out.println(selected);
        ListView<Event> event_list = null;
        Service_Event sp = new Service_Event();
        list.addAll(sp.afficher_nom());
        //System.out.println(sp.afficher_nom());
        //list.removeAll(this.event_list);

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Event> filteredData = new FilteredList<>(dataList, b -> true);
        System.out.println(dataList);
        //System.out.println(filteredData);

        // 2. Set the filter Predicate whenever the filter changes.
        lookup.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Event -> {
                // If filter text is empty, display all 
                //System.out.println(newValue);

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name 
                String lowerCaseFilter = newValue.toLowerCase();

                if (Event.getNom_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    //System.out.println(lowerCaseFilter);
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }

            });
        });
        SortedList<Event> sortedData = new SortedList<>(filteredData);
        //sortedData.toString().substring(sortedData.lastIndexOf("Nom_event") + 1)
        //System.out.println(sortedData);
        //System.out.println(filteredData.toString().substring(filteredData.toString().indexOf("=", filteredData.toString().indexOf("=") + 1), 40) );
        ObservableList<String> observableList = FXCollections.observableList((List) sortedData);
        //System.out.println(observableList);
        this.event_list.setItems(observableList);

    }

    @FXML
    private void getSelected(MouseEvent event) {
        //ListView<Event> event_list = null;
        Event e = new Event(String.valueOf(event_list.getSelectionModel().getSelectedItem()));
        s = e.toString();
        //System.out.println(e);
    }

    public static String getIt() {
        return s;
    }

    @FXML
    private void download(MouseEvent event) {
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(img, "png", fileToSave);
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(Afficher_Event_ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void pdf_Download(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {

        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());

            String file_name = fileToSave.getAbsolutePath();
            Document document = new Document();
            //simple paragraph
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file_name));
            writer.setPageEvent(new PDFBackground());
            //PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            //Paragraph para = new Paragraph("testing");
            Service_Event sp = new Service_Event();
            Service_Map mp = new Service_Map();
            String selected = String.valueOf(sp.get_ID(s));

            FixText(sp.fetch(selected).get(1), 120, 600, writer, 26, (new BaseColor(40, 140, 172)));
            FixText(sp.fetch(selected).get(7), 180, 560, writer, 20, (new BaseColor(99, 241, 255)));
            FixText(sp.fetch(selected).get(3), 180, 480, writer, 20, BaseColor.WHITE);
            FixText(sp.fetch(selected).get(5), 180, 440, writer, 20, BaseColor.WHITE);
            FixText(sp.fetch(selected).get(2), 180, 400, writer, 20, (new BaseColor(99, 241, 255)));
            FixText(sp.fetch(selected).get(4), 180, 360, writer, 20, BaseColor.WHITE);
            FixText(sp.fetch(selected).get(6), 180, 320, writer, 20, BaseColor.WHITE);
            FixText(sp.fetch(selected).get(8), 180, 520, writer, 20, BaseColor.WHITE);
            List<String> lm = mp.fetch(selected);
            FixText("Localisation=(" + lm.get(1) + "," + lm.get(2) + ")", 180, 280, writer, 20, BaseColor.WHITE);
            Service_Categorie_Event sc = new Service_Categorie_Event();
            System.out.println(Integer.valueOf(sp.fetch(selected).get(0).trim()));
            FixText("Catégorie: " + sc.get_Nom(Integer.valueOf(sp.fetch(selected).get(9))), 180, 240, writer, 20, BaseColor.WHITE);
            com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("src/icons/main.png");
            image1.setAbsolutePosition(240f, 640f);
            image1.scaleAbsolute(100, 100);
            document.add(image1);

            com.itextpdf.text.Image image2 = com.itextpdf.text.Image.getInstance("http://" + sp.get_Db_picture(Integer.valueOf(sp.fetch(selected).get(0))));
            image2.setAbsolutePosition(240f, 110f);
            image2.scaleAbsolute(120, 120);
            document.add(image2);

            com.itextpdf.text.Image image3 = com.itextpdf.text.Image.getInstance("http://" + sc.get_Db_picture(sc.get_ID(sc.get_Nom(Integer.valueOf(sp.fetch(selected).get(9))))));
            image3.setAbsolutePosition(150f, 238f);
            image3.scaleAbsolute(20, 20);
            document.add(image3);
            document.close();

        }
    }

    public static void FixText(String text, int x, int y, PdfWriter writer, int size, BaseColor bc) throws IOException {
        try {
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            cb.saveState();
            cb.beginText();
            cb.moveText(x, y);
            cb.setFontAndSize(bf, size);
            cb.setColorFill(bc);
            cb.showText(text);
            cb.endText();
            cb.restoreState();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Event_Client.fxml"));
        Pane root = loader.load();
        AccueilClientController ac= new AccueilClientController();
        BorderPane bb = ac.getBb();
        ac.setBorder(bb);
        ac.getBorder().setCenter(root);
    }

}
