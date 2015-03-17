package org.compiere.model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TicketDetail implements Printable {
	
	private ArrayList<String> headerLines = new ArrayList<String>();
    private ArrayList<String> subHeaderLines = new ArrayList<String>();
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayList<String> totales = new ArrayList<String>();
    private ArrayList<String> footerLines = new ArrayList<String>();
    private Image headerImage = null;
    private String line = null;
    private Font titleFont = new Font("Courier", Font.BOLD, 12);
    private Font subTitleFont = new Font("Courier", Font.PLAIN, 11);
    private Font detailFont = new Font("Courier", Font.PLAIN, 9);
    
    private int count = 0;
    
    //Numero m�ximo de Caract�res por Linea, esto para el caso de 
    //letra tamaño 10
    private static int LINE_DETAIL_MAX_CHAR = 36;
    private static int VALUE_MAX_CHAR = 8;
    private static int DESCRIPTION_MAX_CHAR = 12;
    private static int PRICE_MAX_CHAR = 12;
    private static int LINE_HEADER_MAX_CHAR = 28;
    private static int TOTAL_MAX_CHAR = 16;
    
    private static int TOP_MARGIN = 5;
    private static int LEFT_MARGIN = 0;
    private static int LEF_MARGIN_DESCRIPTION = 17;
    private static int LEF_MARGIN_QUANTITY = 42 ;
    private static int LEF_MARGIN_TOTAL= 40 ;
    
	public ArrayList<String> getFooterLines() {
		return footerLines;
	}
	public void setFooterLines(ArrayList<String> footerLines) {
		this.footerLines = footerLines;
	}
	public Image getHeaderImage() {
		return headerImage;
	}
	public void setHeaderImage(Image headerImage) {
		this.headerImage = headerImage;
	}
	public ArrayList<String> getHeaderLines() {
		return headerLines;
	}
	public void setHeaderLines(ArrayList<String> headerLines) {
		this.headerLines = headerLines;
	}
	public ArrayList<String> getItems() {
		return items;
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
	public ArrayList<String> getSubHeaderLines() {
		return subHeaderLines;
	}
	public void setSubHeaderLines(ArrayList<String> subHeaderLines) {
		this.subHeaderLines = subHeaderLines;
	}
	public ArrayList<String> getTotales() {
		return totales;
	}
	public void setTotales(ArrayList<String> totales) {
		this.totales = totales;
	}
	
	//Metodos para agregar lineas
	public void AddHeaderLine(String line)
    {
        headerLines.add(line);
    }
	
	public void AddSubHeaderLine(String line)
    {
        subHeaderLines.add(line);
    }
	
	public void AddItem(String value,String item,String cantidad, String price)
    {
		items.add(value);
        items.add(item + " ("+cantidad+")");
        items.add(price);
    }

    public void AddTotal(String price)
    {
        totales.add(price);
    }

    public void AddFooterLine(String line)
    {
        footerLines.add(line);
    }  
	/**
	 * Metodo para imprimir el Header del Ticket
	 * @param gfx
	 * @param center
	 */
	public void DrawHeader(Graphics gfx, boolean center)
    {
        for(int i=0 ; i<headerLines.size() ; i++){
    		String header = headerLines.get(i).toString();
            if (header.length() > LINE_HEADER_MAX_CHAR)
            {
            	
            	this.count = this.count + printlargeLine(header, LINE_HEADER_MAX_CHAR, LEFT_MARGIN, center, gfx);
            }
            else
            {
                line = header;
                if(center){
                	drawLineCenter(gfx, line,LINE_HEADER_MAX_CHAR,LEFT_MARGIN,this.count);
                }
                else{
                	gfx.drawString(line, LEFT_MARGIN, (int)yPosition(this.count));
                }

                this.count++;
            }
            
        }
        //dejamos dos espacios
        for(int i=0 ; i<=1 ; i++)
        	drawEspacio(gfx);
    }
	
	/**
	 * Metodo para Imprimir el Subheader del Ticket
	 * @param gfx
	 * @param center
	 */
	private void DrawSubHeader(Graphics gfx, boolean center)
    {
        for(int i=0 ; i<subHeaderLines.size() ; i++){
    		String subHeader = subHeaderLines.get(i).toString();
            if (subHeader.length() > LINE_DETAIL_MAX_CHAR)
            {
            	this.count = this.count + printlargeLine(subHeader, LINE_DETAIL_MAX_CHAR, LEFT_MARGIN, center, gfx);
            }
            else
            {
                line = subHeader;
                if(center){
                	drawLineCenter(gfx, line,LINE_DETAIL_MAX_CHAR,LEFT_MARGIN,this.count);
                }
                else{
                	gfx.drawString(line, LEFT_MARGIN, (int)yPosition(this.count));
                }

                this.count++;
            }
            
        }
        //dejamos tres espacios
        for(int i=0 ; i<1 ; i++)
        	drawEspacio(gfx);
    }
	
	/**
	 * Metodo para imprimir las lineas del detalle del Ticket
	 * @param gfx
	 */
	private void DrawItems(Graphics gfx)
    {
        /*gfx.drawString("CANT  DESCRIPCION           IMPORTE", (int)leftMargin, (int)yPosition());
		count++;
        drawEspacio(gfx);*/
		int valueLines = 0;
		int descriptionLines = 0;
		int priceLines = 0;
		int temp = 0;

		//Aumentamos a i de 3 en tres pues el orden de los elementos esta de la siguiente
		//forma: cantidad, Descripci�n y monto en manera de ciclo (vgarcia)
        for(int i=0 ; i<items.size() ; i=i+3){
        	
        	// Primero imprimimos el value del producto
            line = items.get(i).toString();
            if (line.length() > VALUE_MAX_CHAR)
            {
            	valueLines = printlargeLine(line, VALUE_MAX_CHAR, (int) (LEFT_MARGIN*2.834), false, gfx);
            }
            else
            {
                gfx.drawString(line, (int) (LEFT_MARGIN*2.834), (int)yPosition(this.count));
                valueLines++;
            }
            
            //Ahora imprimimos la descripcion que puede ocupar 'n' renglones
            line = items.get(i+1).toString();
            if (line.length() > DESCRIPTION_MAX_CHAR)
            {
            	descriptionLines = printlargeLine(line, DESCRIPTION_MAX_CHAR, (int) (LEF_MARGIN_DESCRIPTION*2.834), false, gfx);
            }
            else
            {
                gfx.drawString(line, (int) (LEF_MARGIN_DESCRIPTION*2.834), (int)yPosition(this.count));
                descriptionLines++;
            }
            
            //Imprimimos el monto
            line = items.get(i+2).toString(); 
            if (line.length() > PRICE_MAX_CHAR)
            {
            	priceLines = printlargeLine(alignRightText(priceFormat(line), PRICE_MAX_CHAR), PRICE_MAX_CHAR, (int) (LEF_MARGIN_QUANTITY*2.834), true, gfx);
            }
            else
            {
                gfx.drawString(alignRightText(priceFormat(line), PRICE_MAX_CHAR), (int) (LEF_MARGIN_QUANTITY*2.834), (int)yPosition(this.count));
                priceLines++;
            }
            
            //asignamos el numero de renglones al count
            if(descriptionLines>valueLines)
            	if(descriptionLines>priceLines)
            		temp = descriptionLines;
            	else
            		temp = priceLines;
            else if(valueLines>priceLines)
            		temp = valueLines;
            this.count = this.count + temp;
        }
        
        drawEspacio(gfx);
    }
	
	/**
	 * Metodo para imprimir los totales del ticket
	 * @param gfx
	 */
	private void DrawTotales(Graphics gfx)
    {
		drawTotalLine(gfx);
        for(int i=0 ; i<totales.size() ; i++){
        	String total = totales.get(i).toString();
            line ="Total a Pagar:";
            int lim = 36-line.length();
            lim = lim-total.length();
            for(int j=0 ; j<lim ; j++){
            	line = line +" ";
            }
            	line = line +total;
            gfx.drawString(line, LEFT_MARGIN, (int)yPosition(this.count));

            count++;
        }
        drawTotalLine(gfx);
        drawEspacio(gfx);
        drawEspacio(gfx);
    }
	
	private void DrawFooter(Graphics gfx, boolean center)
    {
		for(int i=0 ; i<footerLines.size() ; i++){
    		String footer = footerLines.get(i).toString();
            if (footer.length() > LINE_DETAIL_MAX_CHAR)
            {
            	
            	this.count = this.count + printlargeLine(footer, LINE_DETAIL_MAX_CHAR, LEFT_MARGIN, center, gfx);
            }
            else
            {
                line = footer;
                if(center){
                	drawLineCenter(gfx, line,LINE_DETAIL_MAX_CHAR,LEFT_MARGIN,this.count);
                }
                else{
                	gfx.drawString(line, LEFT_MARGIN, (int)yPosition(this.count));
                }

                this.count++;
            }
            
        }
        //dejamos dos espacios
        for(int i=0 ; i<=1 ; i++)
        	drawEspacio(gfx);
    }
	
	
	/**
	 * metodo para obtener la posicion 'Y' en que debe ubicarse la linea
	 * @param printFont
	 * @return
	 */
    private float yPosition(int y)
    {
        return TOP_MARGIN + (y * titleFont.getSize());
    }
    
    /**
     * Metodo que agrega una linea en blaco
     * @param gfx
     */
    private void drawEspacio(Graphics gfx)
    {
        line = "";

        gfx.drawString(line, LEFT_MARGIN, (int)yPosition(this.count));

        this.count++;
    }
    
    /**
     * Metodo para escribir de manera centrada un linea
     * @param gfx
     * @param linea
     * @param font
     */
    public void drawLineCenter(Graphics gfx,String linea, int limite, int margen, int yPosition){
    	/*
		 * Para las cuando se quiere centrar una linea, llenamos los extremos con
		 * espacios en blanco
		 */
		int spaceNum = 0;
		int leftSpace = 0;
		String temp = "";
		
		if(linea.length() < limite){
			spaceNum = limite-linea.length();

			if(spaceNum%2==0){
				leftSpace = spaceNum/2;
			}else{
				leftSpace = (spaceNum-1)/2;
			}

			for(int j=0 ; j<leftSpace ; j++)
				temp = temp + " ";
			
			linea = temp + linea;
		}
		
		gfx.drawString(linea, margen, (int)yPosition(yPosition));
    }
    
    /**
     * Metodo para obtener las palabras de una linea, se regresa como un ArrayList
     * @param line
     * @return
     */
    public ArrayList<String> getWordsOfLine(String line){
    	
    	ArrayList<String> words = new ArrayList<String>();

    	StringTokenizer tokens=new StringTokenizer(line);
    	while(tokens.hasMoreTokens()){
                words.add(tokens.nextToken());
            }
    	
    	return words;
    }
    
    /**
     * Metodo pat�ra imprimir que tiene mas caracteres de los permitidos por linea
     * @param line1
     * @param charLimit
     * @param margin
     * @param center
     * @param yPosition
     * @param gfx
     * @return
     */
    public int printlargeLine(String line1, int charLimit, int margin, boolean center, Graphics gfx){
    	int linesNum = 0;
    	int localCount = this.count;
    	ArrayList<String> words = new ArrayList<String>();
    	String temp = "";
    	String subLine = "";
    	
    	words = getWordsOfLine(line1);
        for(int m=0 ; m<words.size() ; m++)
        {
        	temp=subLine;
        	if(m>0)
        		subLine = subLine + " " + words.get(m).toString();
        	else
        		subLine = subLine + words.get(m).toString();
        	
        	if(subLine.length()>charLimit){
        		if(center){
                	drawLineCenter(gfx, temp,charLimit,margin,localCount);
                }
                else{
                	gfx.drawString(temp, margin, (int)yPosition(localCount));
                }
        		temp="";
        		subLine = words.get(m).toString() + " ";
        		localCount++;
        	}
        	
        	if(subLine.length()<charLimit && m==words.size()-1){
        		
        		if(center){
                	drawLineCenter(gfx, subLine,charLimit,margin,localCount);
                }
                else{
                	gfx.drawString(subLine, margin, (int)yPosition(localCount));
                }
        		localCount++;
        	}
        }
    	linesNum = localCount-this.count;
    	
    	return linesNum;
    }
    
    /**
     * Este metodo regrese una cadena de caracteres de espacios en nblanco para poder alinear a la derecha 
     * la cadena
     * @param size
     * @return
     */
    private String alignRightText(String string, int maxChar)
    {
    	int length = string.length();
        String espacios = "";
        int spaces = maxChar - length;
        for (int x = 0; x < spaces; x++)
            espacios = espacios + " ";
        
        return espacios + string;
    }
    
    /**
     * Metodo para dar formato al precio
     * @param price
     * @return
     */
    public String priceFormat(String price){
    	
    	String newPrice = "";
    	String subPrice[] = new String[2];
    	int i = 0;
    	int digits = 0;
    	//Character temp = null;
    	
    	StringTokenizer tokens=new StringTokenizer(price,".");
    	while(i<2 && tokens.hasMoreTokens()){
    		subPrice[i]=tokens.nextToken();
    		i++;
            }
    	
    	char[] numbers = subPrice[0].toCharArray();
    	
    	//cada tres digitos ponemos agregamos una coma
    	for(i=numbers.length-1 ; i>-1 ; i--){
    		digits++;
    		if(digits%3 == 0 && digits<numbers.length && numbers[i-1]!='-' )
    			newPrice = "," + numbers[i] + newPrice;
    		else
    			newPrice = numbers[i] + newPrice;
    	}
    	
    	//Agregamos los decimales
    	newPrice = newPrice + "." + subPrice[1];
    	
    	return newPrice;
    	
    }
    
    /**
     * Metodo para imprimir la Linea que separa el total
     * @param gfx
     */
    public void drawTotalLine(Graphics gfx){
    	
    	String totalLine = "                    _________________";
    	gfx.drawString(totalLine, LEFT_MARGIN, (int)yPosition(this.count));
    	this.count++;
    }

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setFont(this.titleFont);
        g2d.translate(0, 0);
        
        DrawHeader(graphics,true);
        g2d.setFont(this.detailFont);
        DrawSubHeader(graphics,true);
        DrawItems(graphics);
        DrawTotales(graphics);
        DrawFooter(graphics,false);
		
        return PAGE_EXISTS;
	}
	
	public void PrintTicket() {
    	PrinterJob job = PrinterJob.getPrinterJob();
    	PageFormat format = job.defaultPage();
    	Paper papel = new Paper();
    	papel.setImageableArea(0, 0,212.55 ,format.getHeight());
    	format.setPaper(papel);
    	job.setPrintable(this, format);
        job.setPrintable(this,format);
        try {
        	job.print();
        	} catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
	}
	
	public static void main(String args[]){
		new TicketDetail();
	}
}
