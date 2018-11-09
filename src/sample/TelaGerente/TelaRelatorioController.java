package sample.TelaGerente;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.LiteralNode;
import sample.ConexaoBanco;
import sample.Main;
import sample.Produto;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class TelaRelatorioController {
    @FXML
   private DatePicker txtDataInicio, txtDataFim;
   private ResultSet rs;
    private float valorTotal;
    public void acaoVoltar() throws IOException {
        Main.trocaTela("TelaGerente/telaGerente.fxml");
    }

    private void consultaBanco(LocalDate data, LocalDate data2){
       

    }

    public void initialize(URL location, ResourceBundle resources){

    }

    public void acaoGerarRelatorioEstoque(ActionEvent actionEvent) {
        LocalDate data = txtDataInicio.getValue();
        LocalDate data2 = txtDataFim.getValue();
        consultaBanco(data, data2);
        Document doc = new Document();
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File file = f.showSaveDialog(new Stage());
        if (file != null) {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsolutePath()));
                doc.open();
                PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement("select nome,descricao,quantidade,medida from produto");
                rs = ps.executeQuery();
                while (rs.next()) {
                    //listaProdutos = rs.getString("nome");
                    doc.add(new Paragraph("Nome do produto: " + rs.getString("nome")));
                    doc.add(new Paragraph("Descrição: "+rs.getString("descricao")));
                    doc.add(new Paragraph("Unidade de medida : "+rs.getString("medida")));
                    doc.add(new Paragraph("Quantidade: "+rs.getInt("quantidade")));
                    doc.add(new Paragraph("                                                "));
                    doc.add(new Paragraph("                                                "));
                }
                doc.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Relatório gerado com sucesso!");
                alert.show();
            } catch (DocumentException e) {
                JOptionPane.showMessageDialog(null, e);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Defina um lugar para salvar o Relarório!");
            alert.show();
        }
    }

    public void acaoGerarRelatorioPedidos(ActionEvent actionEvent) {
            LocalDate data = txtDataInicio.getValue();
            LocalDate data2 = txtDataFim.getValue();
            consultaBanco(data, data2);
            Document doc = new Document();
            FileChooser f = new FileChooser();
            f.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
            File file = f.showSaveDialog(new Stage());
            if (file != null) {
                try {
                    PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsolutePath()));
                    doc.open();
                    PreparedStatement ps = ConexaoBanco.getConnection().prepareStatement("select c.id_mesa,pe.codpedido,pe.precototal from pedido pe, comanda c where pe.codcomanda = c.codcomanda;");
                    rs = ps.executeQuery();
                    doc.add(new Paragraph("RELATÓRIO DAS VENDAS DO RESTAURANTE SEPARADAS POR MESA:"));
                    doc.add(new Paragraph("                                                "));
                    doc.add(new Paragraph("                                                "));
                    doc.add(new Paragraph("                                                "));
                    doc.add(new Paragraph("                                                "));
                    while (rs.next()) {
                        //listaProdutos = rs.getString("nome");
                        doc.add(new Paragraph("         Numero da mesa: " + rs.getInt("id_mesa")));
                        doc.add(new Paragraph("         Codígo do pedido : "+rs.getInt("codpedido")));
                        doc.add(new Paragraph("         Valor total do pedido: "+rs.getFloat("precototal")));
                        doc.add(new Paragraph("                                                "));
                        doc.add(new Paragraph("                                                "));
                        valorTotal = valorTotal + rs.getFloat("precototal");
                    }
                    doc.add(new Paragraph("         Lucro bruto total : "+valorTotal));
                    doc.close();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Relatório gerado com sucesso!");
                    alert.show();
                } catch (DocumentException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,e);
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Defina um lugar para salvar o Relarório!");
                alert.show();
            }
        }
}
