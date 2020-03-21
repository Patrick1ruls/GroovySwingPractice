package gui

import model.Person

import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JPopupMenu
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class TablePanel extends JPanel{
    private JTable table
    private PersonTableModel tableModel
    private JPopupMenu popupMenu
    private PersonTableListener personTableListener

    TablePanel(){
        tableModel = new PersonTableModel()
        table = new JTable(tableModel)
        popupMenu = new JPopupMenu()

        // When right clicking the data table, bring up the delete row popup option
        JMenuItem removeItem = new JMenuItem("Delete Row")
        popupMenu.add(removeItem)

        table.addMouseListener(new MouseAdapter() {
            void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint()) // Grabs the table row when right clicking
                table.getSelectionModel().setSelectionInterval(row, row) // Highlights clicked on row (start row, end row)
                if(e.getButton() == MouseEvent.BUTTON3) { // When right clicking
                    popupMenu.show(table, e.getX(), e.getY()) // Show popup item at mouse click location
                }
            }
        })

        removeItem.addActionListener(new ActionListener() {
            void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow() // Selects the row when clicking the "Delete Row" popup button
                if(personTableListener != null) {
                    personTableListener.rowDeleted(row)
                    tableModel.fireTableRowsDeleted(row, row)
                }
            }
        })

        setLayout(new BorderLayout())

        add(new JScrollPane(table), BorderLayout.CENTER)
    }

    void setData(List<Person> db) {
        tableModel.setData(db)
    }

    void refresh() {
        tableModel.fireTableDataChanged()
    }

    void setPersonTableListener(PersonTableListener listener) {
        this.personTableListener = listener
    }

}
