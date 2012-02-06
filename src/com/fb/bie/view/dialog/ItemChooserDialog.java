package com.fb.bie.view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fb.bie.Data;
import com.fb.bie.model.data.ItemObject;
import com.fb.bie.model.data.ItemObjectDAO;
import com.fb.bie.model.data.ItemTypes;
import com.fb.bie.view.GUI;
import com.fb.bie.view.ui.field.ItemCellRenderer;
import com.fb.bie.view.ui.panel.ItemPanelInterface;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemChooserDialog ***********************************************************
public class ItemChooserDialog extends JDialog {
  private static final long serialVersionUID = 8169291209383530978L;
  private ItemPanelInterface _parent;
  private int                _itemType;
  private boolean            _showControls;
  private ItemObject         _choice;
  private ItemObject[]       _itemObjects;
  private ItemObject         _curItemObject;
  private boolean[]          _filters;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // ItemChooserDialog *********************************************************
  /**
   * Constructor.  Creates modal-less dialog that will hold the ItemObjects.
   * showControls is used to determine if the filter panel is displayed.
   * List is populated with items of the given item type (default is ALL).
   * This dialog will close when:
   * * a buff is chosen (considered an Accept - ChosenItem is set to the clicked Item)
   * * ESC is pressed (considered a Cancel)
   * * 'X' is clicked (considered a Cancel)
   * * any area outside the dialog is clicked (considered a Cancel)
   */
  private ItemChooserDialog( JFrame             owner,
                             ItemPanelInterface parent,
                             boolean            showControls,
                             int                itemType,   
                             ItemObject         curItemObject  ) {
    super( owner );

    _parent        = parent;
    _showControls  = showControls;
    _itemType      = itemType;
    _curItemObject = curItemObject;
    _filters       = resetFilters();

    initGUI();
    populateFields();

  } // ItemChooserDialog -------------------------------------------------------


  // itemChooserDialog +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void itemChooserDialog( JFrame             owner,
                                        ItemPanelInterface parent,
                                        Point              location,
                                        boolean            showControls,
                                        int                itemType,   
                                        ItemObject         curItemObject  ) {
    ItemChooserDialog icd;
    
    icd = new ItemChooserDialog( owner, parent, showControls, itemType, curItemObject );
    icd.pack();
    icd.setLocation( location ); // shifts location up
    icd.setVisible( true );
    
  } // itemChooserDialog -------------------------------------------------------

  
  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setListData +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This method applies the text filter to the items available passed in.
   * Always include "empty"
   */
  private void setListData() {
    ArrayList<ItemObject> filteredList;
    ItemObject[] filteredItems;
    String       filterValue, itemName;
    int          index;

    filteredList  = new ArrayList<ItemObject>();
    filterValue = editFilter.getText().toLowerCase();

    if ( !filterValue.isEmpty() ) {
      
      filteredList.add( _itemObjects[0] );
      for ( index = 1; index< _itemObjects.length; index++ ) {
        itemName = _itemObjects[index].getItemName().toLowerCase();
        if ( itemName.contains( filterValue ) ) {
          filteredList.add( _itemObjects[index] );
        } // if
      } // for

      filteredItems = new ItemObject[filteredList.size()];
      filteredList.toArray( filteredItems );
    } // if
    else { // no filter
      filteredItems = _itemObjects;
    } // else
    
    listItems.setListData( filteredItems );
    
  } // setListData -------------------------------------------------------------
  

  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // populateFields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void populateFields() {
    
    itemCellRenderer.setCurrentObject( _curItemObject );
    _itemObjects = ItemObjectDAO.getItemObjectsByType( _itemType, true );
    setListData();
    editFilter.requestFocusInWindow();
    
  } // populateFields ----------------------------------------------------------
  
  
  // resetFilters ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private boolean[] resetFilters() {
    boolean[] result = new boolean[ItemTypes.NUM_TYPES];

    result[ItemTypes.AMMUNITION]       = true;
    result[ItemTypes.ACCESSORY]  = true;
    result[ItemTypes.ARMOR_HEAD]       = true;
    result[ItemTypes.ARMOR_BODY]       = true;
    result[ItemTypes.ARMOR_LEGS]       = true;
    result[ItemTypes.BUILDING]         = true;
    result[ItemTypes.COIN]             = true;
    result[ItemTypes.CONSUMABLE]       = true;
    result[ItemTypes.FURNITURE]        = true;
    result[ItemTypes.LIGHTING]         = true;
    result[ItemTypes.MISCELLANEOUS]    = true;
    result[ItemTypes.ORE]              = true;
    result[ItemTypes.POTION]           = true;
    result[ItemTypes.STATUE]           = true;
    result[ItemTypes.TOOL_AXE]         = true;
    result[ItemTypes.TOOL_CHAINSAW]    = true;
    result[ItemTypes.TOOL_DRILL]       = true;
    result[ItemTypes.TOOL_HAMAXE]      = true;
    result[ItemTypes.TOOL_HAMMER]      = true;
    result[ItemTypes.TOOL_OTHER]       = true;
    result[ItemTypes.TOOL_PICK]        = true;
    result[ItemTypes.WEAPON_BOOMERANG] = true;
    result[ItemTypes.WEAPON_BOW]       = true;
    result[ItemTypes.WEAPON_EXPLOSIVE] = true;
    result[ItemTypes.WEAPON_FLAIL]     = true;
    result[ItemTypes.WEAPON_GUN]       = true;
    result[ItemTypes.WEAPON_MAGICAL]   = true;
    result[ItemTypes.WEAPON_MELEE]     = true;
    result[ItemTypes.WEAPON_SPEAR]     = true;
    result[ItemTypes.WEAPON_THROWN]    = true;
    
    return result;
  } // resetFilters ------------------------------------------------------------

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // dialogLostFocus +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void dialogLostFocus( WindowEvent event ) {

    if ( event.getOppositeWindow() != null ) {
      if ( SwingUtilities.isDescendingFrom( event.getOppositeWindow(), ItemChooserDialog.this ) ) {
        return;
      } // if
    } // if
    
    pressCancelButton();
    
  } // dialogLostFocus ---------------------------------------------------------

  
  // pressCancelButton +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void pressCancelButton() {
    this.setVisible( false );
  } // pressCancelButton -------------------------------------------------------


  // clickCheckbox +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickCheckbox( ItemEvent event ) {
    Object source;
    int    state;
    
    source = event.getItemSelectable();
    state  = event.getStateChange();

    if ( source == checkAll ) {
        clickCheckAll( state );
    } // if
    else {
      clickItemType( source, state );
      _itemObjects = ItemObjectDAO.getFilteredItems( _filters );
    } // else

    setListData();
    
  } // clickCheckbox -----------------------------------------------------------


  // clickCheckAll +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickCheckAll( int state ) {
    int       index;
    JCheckBox checkbox;
    
    if ( state == ItemEvent.SELECTED ) {
      for ( index = 0; index < panelLimits.getComponentCount(); index++ ) {
        checkbox = (JCheckBox) panelLimits.getComponent( index );
        checkbox.setEnabled(  false );
        checkbox.setSelected( true  );
        _itemObjects = ItemObjectDAO.getAllItems();
      } // for
    } // if

    if ( state == ItemEvent.DESELECTED ) {
      for ( index = 0; index < panelLimits.getComponentCount(); index++ ) {
        checkbox = (JCheckBox) panelLimits.getComponent( index );
        checkbox.setEnabled(  true  );
        checkbox.setSelected( false );
        _itemObjects    = new ItemObject[1];
        _itemObjects[0] = ItemObjectDAO.getEmptyItem();
      } // for
    } // if
    
  } // clickCheckAll -----------------------------------------------------------

  
  // clickItemType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickItemType( Object source,
                              int    state ) {
    // <editor-fold defaultstate="collapsed" desc="clickItemType">
    boolean setting;

    setting = ( state == ItemEvent.SELECTED ) ? true : false;

    if ( source == checkAccess ) { _filters[ItemTypes.ACCESSORY] = setting; } // if
    
    if ( source == checkArmor ) {
      _filters[ItemTypes.ARMOR_HEAD] = setting;
      _filters[ItemTypes.ARMOR_BODY] = setting;
      _filters[ItemTypes.ARMOR_LEGS] = setting;
    } // if
    
    if ( source == checkAmmunition ) { _filters[ItemTypes.AMMUNITION] = setting; } // if
    if ( source == checkBuilding   ) { _filters[ItemTypes.BUILDING]   = setting; } // if
    if ( source == checkConsumable ) { _filters[ItemTypes.CONSUMABLE] = setting; } // if
    if ( source == checkFurniture  ) { _filters[ItemTypes.FURNITURE]  = setting; } // if
    if ( source == checkLghting    ) { _filters[ItemTypes.LIGHTING]   = setting; } // if

    if ( source == checkMisc ) { 
      _filters[ItemTypes.COIN]          = setting; 
      _filters[ItemTypes.MISCELLANEOUS] = setting; 
    } // if
    
    if ( source == checkOres    ) { _filters[ItemTypes.ORE]    = setting; } // if
    if ( source == checkPotions ) { _filters[ItemTypes.POTION] = setting; } // if
    if ( source == checkStatues ) { _filters[ItemTypes.STATUE] = setting; } // if
    
    if ( source == checkTools ) { 
      _filters[ItemTypes.TOOL_AXE]         = setting; 
      _filters[ItemTypes.TOOL_CHAINSAW]    = setting; 
      _filters[ItemTypes.TOOL_DRILL]       = setting; 
      _filters[ItemTypes.TOOL_HAMAXE]      = setting; 
      _filters[ItemTypes.TOOL_HAMMER]      = setting; 
      _filters[ItemTypes.TOOL_OTHER]       = setting; 
      _filters[ItemTypes.TOOL_PICK]        = setting; 
    } // if
    
    if ( source == checkWeaponsAll     ) { 
      clickCheckWeapons( setting );
    } // if
    
    if ( source == checkWeaponsMagical ) {
      _filters[ItemTypes.WEAPON_MAGICAL]   = setting; 
    } // if
 
    if ( source == checkWeaponsMelee  ) {
      _filters[ItemTypes.WEAPON_MELEE]     = setting; 
      _filters[ItemTypes.WEAPON_SPEAR]     = setting; 
    } // if
    
    if ( source == checkWeaponsRanged ) {
      _filters[ItemTypes.WEAPON_BOOMERANG] = setting; 
      _filters[ItemTypes.WEAPON_BOW]       = setting; 
      _filters[ItemTypes.WEAPON_FLAIL]     = setting; 
      _filters[ItemTypes.WEAPON_GUN]       = setting; 
      _filters[ItemTypes.WEAPON_THROWN]    = setting; 
      _filters[ItemTypes.WEAPON_EXPLOSIVE] = setting; 
    } // if
    
    // </editor-fold>
  } // clickItemType -----------------------------------------------------------


  // clickCheckWeapons +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickCheckWeapons( boolean setting ) {
    
    _filters[ItemTypes.WEAPON_MAGICAL]   = setting; 
    checkWeaponsMagical.setEnabled( !setting );
    checkWeaponsMelee.setEnabled(   !setting );
    checkWeaponsRanged.setEnabled(  !setting );
    checkWeaponsMagical.setSelected( setting );
    checkWeaponsMelee.setSelected(   setting );
    checkWeaponsRanged.setSelected(  setting );
    
  } // clickCheckWeapons -------------------------------------------------------
  
  
  // clickItemList +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickItemList( ListSelectionEvent event ) {

    if ( !event.getValueIsAdjusting() ) {
      if ( !listItems.isSelectionEmpty() ) {
        _choice = (ItemObject) listItems.getSelectedValue();
        if ( _choice != null ) {
          if ( _choice.getItemId() != 603 ) { // carrot
          _parent.chooseItemPanel( _choice );
          } // if
          else {
            JOptionPane.showMessageDialog( Data.getView(), 
                                           Data.NO_CARROT,
                                           Data.NO_CARROT_TITLE,
                                           JOptionPane.ERROR_MESSAGE );
          } // else
        } // if
        Data.setInventoryDirty();
        this.setVisible( false );
      } // if
    } // if

  } // clickItemList -----------------------------------------------------------

  
  // registerEscapeKey +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void registerEscapeKey() {
    this.getRootPane().registerKeyboardAction( new ActionListener() {  @Override
            public void actionPerformed( ActionEvent event ) { pressCancelButton(); } },
            KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0 ),
            JComponent.WHEN_IN_FOCUSED_WINDOW );
  } // registerEscapeKey -------------------------------------------------------


  // requestFilterFocus ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void requestFilterFocus() {
    editFilter.requestFocusInWindow();
  } // requestFilterFocus ------------------------------------------------------

  
  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.setLayout( new BorderLayout() );
    this.setUndecorated( true );
    this.setBackground( GUI.colorChooserBack );
    this.addWindowFocusListener( listenerWindowFocus );
    this.setAlwaysOnTop( true );
    registerEscapeKey();
    
    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    panelBorder   = new JPanel(  new GridLayout( 1, 1 ) );
    panelBorder.setBackground(   GUI.colorChooserBack   );
    panelContent  = new JPanel(  new BorderLayout()     );
    panelControls = new JPanel(  new BorderLayout()     );
    panelControls.setBorder(     GUI.borderChooserCtls  );
    panelControls.setBackground( GUI.colorChooserFront  );
    
    checkAll    = setupCheckbox( "All" );
    panelFilter = new JPanel( new BorderLayout() );
    labelFilter = new JLabel( "     Filter: " );
    panelFilter.setBackground( GUI.colorChooserFront );
    editFilter  = new JTextField( "" );
    editFilter.setBorder( GUI.borderEditField );
    editFilter.getDocument().addDocumentListener( listenerDocumentFilter );
    editFilter.setToolTipText( "Enter a filter to limit items listed" );
    labelSpacer = new JLabel( "     " );
    
    panelLimits = new JPanel( new GridLayout( 0, 4 ) );
    panelLimits.setBackground(  GUI.colorChooserFront  );
    panelLimits.setBorder(      GUI.borderChooserCtls  );
    panelLimits.setToolTipText( "Limit Items by Type"  );
    checkAccess         = setupCheckbox( "Access"    );
    checkAmmunition     = setupCheckbox( "Ammo"      );
    checkArmor          = setupCheckbox( "Armor"     );
    checkBuilding       = setupCheckbox( "Building"  );
    checkCoins          = setupCheckbox( "Coins"     );
    checkConsumable     = setupCheckbox( "Consum"    );
    checkFurniture      = setupCheckbox( "Furniture" );
    checkLghting        = setupCheckbox( "Lighting"  );
    checkMisc           = setupCheckbox( "Misc"      );
    checkOres           = setupCheckbox( "Ores"      );
    checkPotions        = setupCheckbox( "Potions"   );
    checkStatues        = setupCheckbox( "Statues"   );
    checkTools          = setupCheckbox( "Tools"     );
    checkWeaponsAll     = setupCheckbox( "Weapons"   );
    checkWeaponsMagical = setupCheckbox( "Magical"   );
    checkWeaponsMelee   = setupCheckbox( "Melee"     );
    checkWeaponsRanged  = setupCheckbox( "Ranged"    );
    
    buttonCancel = new JButton(   "X" );
    buttonCancel.setFocusPainted( false );
    buttonCancel.setToolTipText(  "Cancel item selection" );
    buttonCancel.addActionListener( listenerButtonCancel );

    scrollList = new JScrollPane();
    scrollList.setVerticalScrollBarPolicy(   ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS  );
    scrollList.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );

    panelList = new JPanel( new BorderLayout() );
    panelList.setBackground( GUI.colorChooserFront );
    listItems = new JList(); 
    listItems.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    itemCellRenderer = new ItemCellRenderer();
    listItems.setCellRenderer(  itemCellRenderer  );
    listItems.setBackground(    GUI.colorItemCell );
    listItems.addListSelectionListener( listenerItemList );

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( panelBorder );
    panelBorder.add(     panelContent,  BorderLayout.CENTER );
    panelContent.add(    panelControls, BorderLayout.NORTH  );
      panelControls.add( checkAll,      BorderLayout.WEST   );
      panelControls.add( panelFilter,   BorderLayout.CENTER );
       panelFilter.add(  labelFilter,   BorderLayout.WEST   );
       panelFilter.add(  editFilter,    BorderLayout.CENTER );
       panelFilter.add(  labelSpacer,   BorderLayout.EAST   );
      panelControls.add( buttonCancel,  BorderLayout.EAST   );

    if ( _showControls ) {
      checkAll.setEnabled( true );
      panelContent.add( panelLimits, BorderLayout.CENTER );
       panelLimits.add( checkAccess         );
       panelLimits.add( checkArmor          );
       panelLimits.add( checkAmmunition     );
       panelLimits.add( checkBuilding       );
     //panelChecks.add( checkCoins          ); // included in Misc
       panelLimits.add( checkConsumable     );
       panelLimits.add( checkFurniture      );
       panelLimits.add( checkLghting        );
       panelLimits.add( checkMisc           );
       panelLimits.add( checkOres           );
       panelLimits.add( checkPotions        );
       panelLimits.add( checkStatues        );
       panelLimits.add( checkTools          );
       panelLimits.add( checkWeaponsAll     );
       panelLimits.add( checkWeaponsMelee   );
       panelLimits.add( checkWeaponsRanged  );
       panelLimits.add( checkWeaponsMagical );
     } // if
     else {
      checkAll.setEnabled( false );
     } // else

    panelContent.add( panelList,  BorderLayout.SOUTH  );
     panelList.add(   scrollList, BorderLayout.CENTER );
      scrollList.getViewport().add( listItems );

  } // initGUI -----------------------------------------------------------------

  
  // setupCheckbox +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private JCheckBox setupCheckbox( String text ) {
    JCheckBox result;

    result = new JCheckBox( text, true );
    result.setBackground( GUI.colorChooserFront );
    result.setEnabled( false );
    result.setFocusPainted( false );
    result.addItemListener( listenerCheckbox );

    return result;
  } // setupCheckbox -----------------------------------------------------------
  
  
  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  JPanel      panelBorder;
  JPanel      panelContent;
  JPanel      panelControls;
  JCheckBox   checkAll;
  JPanel      panelFilter;
  JLabel      labelFilter;
  JLabel      labelSpacer;
  JTextField  editFilter;
  
  JPanel      panelLimits;
  JCheckBox   checkAccess;
  JCheckBox   checkAmmunition;
  JCheckBox   checkArmor;
  JCheckBox   checkBuilding;
  JCheckBox   checkCoins;
  JCheckBox   checkConsumable;
  JCheckBox   checkFurniture;
  JCheckBox   checkLghting;
  JCheckBox   checkMisc;
  JCheckBox   checkOres;
  JCheckBox   checkPotions;
  JCheckBox   checkStatues;
  JCheckBox   checkTools;
  JCheckBox   checkWeaponsAll;
  JCheckBox   checkWeaponsMagical;
  JCheckBox   checkWeaponsMelee;
  JCheckBox   checkWeaponsRanged;

  JButton     buttonCancel;

  JScrollPane scrollList;
  JPanel      panelList;
  ItemCellRenderer itemCellRenderer;
  JList       listItems; 

  
  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/  
  WindowFocusListener listenerWindowFocus = new WindowFocusListener() {
    @Override public void windowGainedFocus( WindowEvent event ) { requestFilterFocus();     }
    @Override public void windowLostFocus(   WindowEvent event ) { dialogLostFocus( event ); }    
  }; // listenerWindowFocus

  ActionListener listenerButtonCancel = new ActionListener() { @Override
    public void actionPerformed( ActionEvent event ) { pressCancelButton();     } };

  ItemListener listenerCheckbox = new ItemListener() { @Override
    public void itemStateChanged( ItemEvent event ) {  clickCheckbox( event );  } };
  
  ListSelectionListener listenerItemList = new ListSelectionListener() { @Override
    public void valueChanged( ListSelectionEvent event ) { clickItemList( event ); } };

  DocumentListener listenerDocumentFilter = new DocumentListener() {
    @Override public void insertUpdate(  DocumentEvent event ) { setListData(); }
    @Override public void removeUpdate(  DocumentEvent event ) { setListData(); }
    @Override public void changedUpdate( DocumentEvent event ) { setListData(); }
  }; // listenerDocumentName ---------------------------------------------------


} // ItemChooserDialog =========================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
