package controller.XML;

import model.CreditData;
import model.CreditType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Yar
 */
public class XMLReader {

    private Document doc;
    private DocumentBuilder builder;
    private File file;


    public XMLReader(File file){
        this.file = file;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(this.file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CreditData parse(){

            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();
            NodeList params = root.getChildNodes();

            String id = new String();
            String amount = new String();
            String interestRate = new String();
            String duration = new String();
            String startDate = new String();
            String creditType = new String();
            String firstContribution = new String();
            String service = new String();

            for (int i = 0; i < params.getLength(); i++){
                Node param = params.item(i);
                if (param.getNodeType() != Node.TEXT_NODE){
                    System.out.println(param.getNodeName() + " " + param.getAttributes().getNamedItem("val").getNodeValue());
                    if(param.getNodeName() == "id"){
                        id = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "amount"){
                        amount = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "interestRate"){
                        interestRate = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "duration"){
                        duration = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "startDate"){
                        startDate = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "creditType"){
                        creditType = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "firstContribution"){
                        firstContribution = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                    if(param.getNodeName() == "service"){
                        service = param.getAttributes().getNamedItem("val").getNodeValue();
                    }
                }

            }

        return new CreditData.CreditBuilder(Integer.parseInt(id))
                .amount(new BigDecimal(amount))
                .interestRateYear(new BigDecimal(interestRate))
                .duration(Integer.parseInt(duration))
                .startDate(LocalDate.parse(startDate))
                .type(CreditType.ofID(Integer.parseInt(creditType)))
                .firstContribution(new BigDecimal(firstContribution))
                .creditServicePercent(new BigDecimal(service))
                .build();
    }
}
