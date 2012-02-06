package com.fb.bie.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import au.com.bytecode.opencsv.CSVReader;

import com.fb.bie.BuildariaInventoryEditor;
import com.fb.bie.Data;
import com.fb.bie.model.data.InventoryItemObject;
import com.fb.bie.model.data.InventoryListObject;
import com.fb.bie.model.data.InventoryObject;
import com.fb.bie.model.data.InventoryObjectDAO;
import com.fb.bie.model.data.ItemObject;
import com.fb.bie.model.data.ItemObjectDAO;
import com.fb.bie.model.data.ItemTypes;
import com.fb.bie.view.Splash;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// Model ***********************************************************************
public class Model {


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // Model +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Loaded:
   * + Item Objects
   */
  public Model() {
    try {
      ItemObjectDAO.setItemObjects( loadItemObjects( Data.ITEM_FILE ) );
    } // try
    catch ( IOException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "I/O Error: " + error.getMessage(),
                                     "I/O Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch
    catch ( BadDataTypeException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "Bad Data Type Error: " + error.getMessage(),
                                     "Bad Data Type Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch


  } // Model -------------------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // loadItemObjects +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private ArrayList<ItemObject> loadItemObjects( String      sourceFile )
                         throws IOException, BadDataTypeException  {
    ArrayList<ItemObject> result = new ArrayList<ItemObject>();
    String[]    line;
    InputStream stream;
    CSVReader   reader = null;
    int         num, type, coun;
    String      name = "";

    try {
      stream = getClass().getClassLoader().getResourceAsStream( sourceFile );
      reader = new CSVReader( new BufferedReader( new InputStreamReader( stream ) ) );
    } // try
    catch ( NullPointerException notUsed ) {
      throw new IOException( "Unable to open file: " + sourceFile );
    } // catch

    while ( ( line = reader.readNext() ) != null) {

      if ( ( !line[0].startsWith("#") ) && ( line[0].trim().length() ) > 0 ) {  // skip comment & blank
        if ( line.length == 4 ) {
          num  = Data.getIntegerValue(  line[0].trim() );
          name =                        line[1].trim()  ;
          type = ItemTypes.getItemType( line[2].trim() );
          coun = Data.getIntegerValue(  line[3].trim() );
          result.add( new ItemObject( num, name, type, coun ) );
        } // if
        else {
          throw new BadDataTypeException( "! Invalid data line: (" + sourceFile + "):\n" + line[1] );
        } // else
      } // if
      Splash.updateSplashProgress( 1 );

    } // while

    if ( reader != null ) { reader.close(); } // if
    if ( stream != null ) { stream.close(); } // if

    return result;
  } // loadItemObjects ---------------------------------------------------------


  // loadImageIcon +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static ImageIcon loadImageIcon( String imageFile ) {
    ImageIcon   result = null;
    InputStream stream;

    try {
      stream = Model.class.getClassLoader().getResourceAsStream( imageFile );
      result = new ImageIcon( ImageIO.read( stream )  );
    } // try
    catch ( IllegalArgumentException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "Image Load Error: " + imageFile + "\n" + error.getMessage(),
                                     "Image Load Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch
    catch ( IOException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "Image Load Error: " + error.getMessage(),
                                     "Image Load Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch
    catch ( NullPointerException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "Image Load Error: " + error.getMessage(),
                                     "Image Load Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch

    return result;
  } // loadImageIcon -----------------------------------------------------------


  /*****************************************************************************
  * File I/O                                                                   *
  *****************************************************************************/
  // loadInventory +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static InventoryObject loadInventory( File sourceFile )
                                        throws JAXBException, FileNotFoundException {
    InventoryObject result = new InventoryObject();
    JAXBContext     context;
    Unmarshaller    jaxbParser;

    context    = JAXBContext.newInstance( InventoryObject.class );
    jaxbParser = context.createUnmarshaller();
    result     = (InventoryObject) jaxbParser.unmarshal( new FileReader( sourceFile ) );

    //printInventory( result ); // DEV print inventory unmarshal

    return result;
  } // loadInventory -----------------------------------------------------------



  // saveInventory +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void saveInventory( File inventoryFile )
                             throws IOException, JAXBException {
    JAXBContext context    = JAXBContext.newInstance( InventoryObject.class );
    Marshaller  jaxbParser = context.createMarshaller();
    InventoryObject inventoryObject;
    Writer      writer     = null;

    try {
      inventoryObject = InventoryObjectDAO.getInventoryObject();
      writer = new FileWriter( inventoryFile );
      jaxbParser.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
      jaxbParser.marshal( inventoryObject, writer );
    } // try
    finally {
      try {
        writer.close();
      } // try
      catch ( IOException error ) {
        JOptionPane.showMessageDialog( Data.getView(),
                                       "I/O Error Saving Inventory File: " + error.getMessage(),
                                       "I/O Error: " + Data.APP_TITLE,
                                       JOptionPane.ERROR_MESSAGE );
        BuildariaInventoryEditor.throwError();
      } // catch
    } // finally

  } // saveInventory -----------------------------------------------------------


  // printInventoryIter ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void printInventoryIter( InventoryObject inventory ) {
    ArrayList<InventoryListObject> inventoryLists;
    Iterator<InventoryListObject>  inventoryListObjects;
    InventoryListObject            inventoryListObject;
    ArrayList<InventoryItemObject> inventoryItemLists;
    Iterator<InventoryItemObject>  inventoryItemListObjects;
    InventoryItemObject            inventoryItemObject;

    inventoryLists = inventory.getInventorysLists();
    inventoryListObjects = inventoryLists.iterator();
    while ( inventoryListObjects.hasNext() ) {

      inventoryListObject = (InventoryListObject) inventoryListObjects.next();
      if ( inventoryListObject != null ) {
        //System.out.println( "List Name: " + inventoryListObject.getListName() );            // DEV println
        inventoryItemLists = inventoryListObject.getInventorysItems();
        inventoryItemListObjects = inventoryItemLists.iterator();
        while ( inventoryItemListObjects.hasNext() ) {

          inventoryItemObject = inventoryItemListObjects.next();
          if ( inventoryItemObject != null ) {

            //System.out.println( "List Name: " + inventoryListObject.getListName() +
            //                    " : Item: " + inventoryItemObject.getItemName() +
            //                    " : Id  : " + inventoryItemObject.getItemId() ); // DEV println

          } // if

        } // while

      } // if

    } // while

  } // printInventoryIter ------------------------------------------------------


} // Model =====================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
