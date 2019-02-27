package controller.XML;

import model.CreditData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * @author Yar
 */
public class XMLWriter {
    private CreditData data;
    private static String path;
    private static DocumentBuilderFactory factory;
    private static Document doc;

    static {
        System.out.println("Static block initialized");
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

    }

    public XMLWriter(){

    }

    public static void createDocument(CreditData data){
        try {
            doc = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element root = doc.createElement("root");
        root.setAttribute("xmlns", "ru.mperika");
        doc.appendChild(root);

        Element id = doc.createElement("id");
        id.setAttribute("val", data.getId()+"");
        root.appendChild(id);

        Element amount = doc.createElement("amount");
        amount.setAttribute("val", data.getAmount().toPlainString());
        root.appendChild(amount);

        Element interestRate = doc.createElement("interestRate");
        interestRate.setAttribute("val", data.getInterestRateYear().toPlainString());
        root.appendChild(interestRate);

        Element duration = doc.createElement("duration");
        duration.setAttribute("val", data.getDuration()+"");
        root.appendChild(duration);

        Element startDate = doc.createElement("startDate");
        startDate.setAttribute("val", data.getStartDate().toString());
        root.appendChild(startDate);

        Element creditType = doc.createElement("creditType");
        creditType.setAttribute("val", data.getType().getId()+"");
        root.appendChild(creditType);

        Element firstContribution = doc.createElement("firstContribution");
        firstContribution.setAttribute("val", data.getFirstContribution().toPlainString());
        root.appendChild(firstContribution);

        Element service = doc.createElement("service");
        service.setAttribute("val", data.getCreditServicePercent().toPlainString());
        root.appendChild(service);
    }

    public static File createFile(String path){
        File file = new File(path);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return file;
    }
}
