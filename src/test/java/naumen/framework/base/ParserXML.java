package naumen.framework.base;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

/** Выполняет парсинг xml файлов
 */
public class ParserXML extends BaseEntity{


	private String fileName;
	/** Базовый конструктор
	 * @param pathToFile путь к xml файлу
	 */
	public ParserXML(String pathToFile){
		fileName = pathToFile;
	}

	/**
	 * @param path путь к файлу
	 * @param isTarget не имеет значения
	 */
	public ParserXML(String path, boolean isTarget){
		fileName = path;
	}

	/** Возвращает текст первого найденного элемента
	 * @param xpathExpression xpath элемента
	 * @return текст элемента
	 */
  public String parse(String xpathExpression){
	  String empty = "";
	  DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	  domFactory.setNamespaceAware(false);
	  try{
		  DocumentBuilder builder = domFactory.newDocumentBuilder();
		  Document doc = builder.parse(new File("").getAbsolutePath()+"\\" + fileName);
		  XPath xpath = XPathFactory.newInstance().newXPath();
		  // XPath Query for showing all nodes value
		  XPathExpression expr = xpath.compile(xpathExpression +"/text()");
		  Object result = expr.evaluate(doc, XPathConstants.NODESET);
		  NodeList nodes = (NodeList) result;
		  return nodes.item(0).getNodeValue();
	  }catch(ParserConfigurationException e){
		  logger.fatal("Ошибка конфигурации парсера: " + e.getMessage());
		  return empty;
	  }catch (SAXException e) {
		  logger.fatal("Ошибка SAX парсера: " + e.getMessage());
		  return empty;
	  }catch(IOException e){
		  logger.fatal("Ошибка чтения документа xml: " + e.getMessage());
		  return empty;
	  }catch (XPathExpressionException e) {
		  logger.fatal("Ошибка xpath локатора: " + e.getMessage());
		  return empty;
	  }catch (NullPointerException e) {
		  logger.warn("Не найден элемент по xpath: " + xpathExpression);
		  return empty;
	  }
  }

  	@Override
	protected String formatLogMsg(String message) {
		return null;
	}
}
