package arduinodatacatcher;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEventListener;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import panamahitek.PanamaHitek_Arduino;
import panamahitek.PanamaHitek_multiMessage;
import java.util.List;

public class Functions {

    private int sensors = 0;
    private int Sensores = 0;
    private String[] Columnas;
    private PanamaHitek_multiMessage Multi;
    private int readings = 1;
    private PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();

    public void addSensors(JButton addButton, JLabel cantidad, DefaultTableModel model, Frame form) {
        try {
            if (sensors > 14) {
                addButton.setEnabled(false);
                throw new Exception("El número máximo de sensores que se puede manejar es 15");
            } else {
                sensors++;
                Sensores++;
            }
            cantidad.setText("Cantidad de sensores: " + sensors);
            model.addRow(new Object[]{sensors, ""});

        } catch (Exception e) {
            JOptionPane.showMessageDialog(form, e.getMessage());
        }

    }
    
    public PanamaHitek_multiMessage getMultiClass(){
        return Multi;
    }

    public boolean connection(boolean SetInterface, JTable table, DefaultTableModel model, JButton start, JButton stop, JButton export, String comboValue, String comboBaudRate, SerialPortEventListener evento) {
        boolean value;
        if (SetInterface == true) {
            int rows = table.getRowCount() - 1;
            for (int i = 0; i <= rows; i++) {
                model.removeRow(0);
            }
            readings = 1;
            start.setText("Iniciar Toma de Datos");
            stop.setEnabled(false);
            export.setEnabled(false);
        }
        try {
            String COM = comboValue;
            int BaudRate = Integer.parseInt(comboBaudRate);
            Arduino.arduinoRXTX(COM, BaudRate, evento);

            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con Arduino de manera exitosa");
            value = true;
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje de la Aplicación", JOptionPane.ERROR_MESSAGE);
            value = false;
        }
        newMulti();
        return value;

    }

    public String[] columnsValue() {
        return Columnas;
    }

    /**
     * Método para crear el Archivo de la hoja de cálculo de MS Excel
     *
     * @param book Se requiere un HSSFWorkbook (librería POI)
     */
    public void createFile(HSSFWorkbook book) {
        JFileChooser Ventana = new JFileChooser();
        String ruta = "";
        try {
            if (Ventana.showSaveDialog(null) == Ventana.APPROVE_OPTION) {
                ruta = Ventana.getSelectedFile().getAbsolutePath() + ".xls";
                FileOutputStream Fichero = new FileOutputStream(ruta);
                book.write(Fichero);
                Fichero.close();
            }
            JOptionPane.showMessageDialog(null, "El archivo '" + Ventana.getSelectedFile().getName() + "' fue exportado con éxito", "Mensaje de la Aplicación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje de la Aplicación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DataCheck_RealTime(DefaultTableModel model, boolean numero, boolean tiempo, JTable tableSensors) {
        int index = 1;
        sensors = Sensores;
        for (int i = 1; i <= model.getRowCount(); i++) {

            if (!model.getValueAt(i - 1, 0).toString().equals("" + i)) {
                model.setValueAt(i, i - 1, 0);
            }

            if (model.getValueAt(i - 1, 1).toString().isEmpty()) {
                model.setValueAt("Sensor " + i, i - 1, 1);
            }
        }

        if (tiempo == true) {
            sensors++;
        }
        if (numero == true) {
            sensors++;
        }
        Columnas = new String[sensors + 1];

        if ((tiempo == true) && (numero == true)) {
            Columnas[1] = "readings Nº";
            Columnas[2] = "Hora";
            index = 3;
        }
        if ((tiempo == false) && (numero == true)) {
            Columnas[1] = "readings Nº";
            index = 2;
        }

        if ((tiempo == true) && (numero == false)) {
            Columnas[1] = "Hora";
            index = 2;
        }

        for (int i = 0; i <= tableSensors.getRowCount() - 1; i++) {
            Columnas[index] = tableSensors.getValueAt(i, 1).toString();
            index++;
        }
    }

    public void enableComponents(JPanel panel, int valor) {
        boolean valorEnable = true;
        Component[] components = panel.getComponents();
        switch (valor) {
            case 1:
                valorEnable = true;
                break;
            case 2:
                valorEnable = false;
                break;
        }

        for (int i = 0; i < components.length; i++) {
            if ((components[i] instanceof JComboBox) || (components[i] instanceof JButton)) {
                components[i].setEnabled(valorEnable);
            }
        }
    }

    public void ExcelDataExport(JTable table, boolean numero, boolean tiempo) {
        HSSFWorkbook libro = new HSSFWorkbook();
        HSSFSheet hoja = libro.createSheet();
        HSSFRow fila = hoja.createRow(0);
        HSSFCell celda = fila.createCell(0);
        celda.setCellValue("Datos Obtenidos");
        fila = hoja.createRow(1);

        for (int i = 0; i <= table.getRowCount() - 1; i++) {
            celda = fila.createCell(i);
            celda.setCellValue(Columnas[i + 1]);
        }

        for (int i = 0; i <= table.getRowCount() - 1; i++) {
            fila = hoja.createRow(i + 2);

            for (int j = 0; j <= table.getColumnCount() - 1; j++) {
                celda = fila.createCell(j);

                if ((numero == true) && (tiempo == true)) {

                    if (j == 1) {
                        celda.setCellValue(table.getValueAt(i, j).toString());
                    } else {
                        celda.setCellValue(Double.parseDouble(table.getValueAt(i, j).toString()));
                    }

                } else if ((numero == false) && (tiempo == true)) {

                    if (j == 0) {
                        celda.setCellValue(table.getValueAt(i, j).toString());
                    } else {
                        celda.setCellValue(Double.parseDouble(table.getValueAt(i, j).toString()));
                    }
                } else if ((numero == true) && (tiempo == false)) {

                    celda.setCellValue(Double.parseDouble(table.getValueAt(i, j).toString()));

                } else {

                    celda.setCellValue(Double.parseDouble(table.getValueAt(i, j).toString()));

                }

            }
        }
        createFile(libro);
    }

    public void exit() {
        int valor = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?");
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void killConnection() {
        try {
            Arduino.killArduinoConnection();
        } catch (Exception ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newMulti() {

        Multi = new PanamaHitek_multiMessage(Sensores, Arduino);

    }

    public void Refresh(JPanel panel, JComboBox comboPorts) {
        try {
            if (Arduino.SerialPortsAvailable() > 0) {
                enableComponents(panel, 1);
                comboPorts.removeAllItems();

                Enumeration listaPuertos = CommPortIdentifier.getPortIdentifiers();
                CommPortIdentifier idPuerto = null;
                while (listaPuertos.hasMoreElements()) {
                    idPuerto = (CommPortIdentifier) listaPuertos.nextElement();
                    if (idPuerto.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                        comboPorts.addItem(idPuerto.getName());
                    }
                }
                JOptionPane.showMessageDialog(null, "Los puertos se han cargado con éxito", "Mensaje de la Aplicación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                enableComponents(panel, 2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mensaje de la Aplicación", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void removeSensor(JButton addButton, JButton removeButton, JLabel cantidad, DefaultTableModel model) {
        if (sensors != 0) {
            addButton.setEnabled(true);
            sensors--;
            Sensores--;
            cantidad.setText("Cantidad de sensores: " + sensors);
            model.removeRow(model.getRowCount() - 1);
        } else {
            removeButton.setEnabled(false);
        }

    }

    public String TiempoTranscurrido() {
        String Output = "";
        String hour;
        String minute;
        String seconds;
        Calendar Calendario = Calendar.getInstance();

        hour = Calendario.get(Calendar.HOUR_OF_DAY) + "";
        minute = Calendario.get(Calendar.MINUTE) + "";
        seconds = Calendario.get(Calendar.SECOND) + "";

        if (Integer.parseInt(hour) < 10) {
            hour = "0" + hour;
        }
        if (Integer.parseInt(minute) < 10) {
            minute = "0" + minute;
        }
        if (Integer.parseInt(seconds) < 10) {
            seconds = "0" + seconds;
        }
        Output = hour + ":" + minute + ":" + seconds;

        return Output;
    }

    public void Update(JTable table, DefaultTableModel model, boolean DataReception, boolean Numero, boolean Tiempo) {

        try {
            if (DataReception == true) {

                List<String> sens = new ArrayList<String>();

                if (Numero) {
                    sens.add(readings + "");
                }

                if (Tiempo) {
                    sens.add(TiempoTranscurrido());
                }
                Multi.getMessageList().forEach(i -> sens.add(i));
                model.addRow(new Object[]{""});

                for (int i = 0; i <= sens.size() - 1; i++) {
                    model.setValueAt(sens.get(i), model.getRowCount() - 1, i);
                }

                readings++;
            }
            table.setCellSelectionEnabled(true);
            table.changeSelection(table.getRowCount() - 1, 0, false, false);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Mensaje de la Aplicación", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para exportar los archivos de la tabla a una hoja de cálculo de
     * Excel
     *
     * @param table JTable donde están contenidos los datos que se desean
     * exportar
     * @param tiempo Variable tipo boolean. TRUE si se desea registrar el tiempo
     * de cada medición.
     * @param Sensores Cantidad de sensores que se están leyendo
     * @param Sensors_names Los nombres de los sensores asignados por el usuario
     */
    public void Excel_Export(JTable table, boolean tiempo, int Sensores, String[] Sensors_names) {
        HSSFWorkbook libro = new HSSFWorkbook();

        CellStyle style = libro.createCellStyle();

        HSSFSheet hoja = libro.createSheet();
        HSSFRow fila = hoja.createRow(0);
        HSSFCell celda = fila.createCell(0);
        celda.setCellStyle(style);

        int i = 0;
        int j = 0;
        try {
            celda.setCellValue("Datos Obtenidos");
            fila = hoja.createRow(1);
            int index = 1;

            celda = fila.createCell(0);
            celda.setCellValue("readings Nº");

            if (tiempo) {
                celda = fila.createCell(1);
                celda.setCellValue("Hora (HH:MM:SS)");
                index++;
            }

            for (i = 0; i <= Sensores - 1; i++) {
                celda = fila.createCell(i + index);
                celda.setCellValue(Sensors_names[i + 1]);
            }

            for (i = 0; i <= table.getRowCount() - 1; i++) {
                fila = hoja.createRow(i + 2);

                for (j = 0; j <= table.getColumnCount() - 1; j++) {
                    celda = fila.createCell(j);
                    if (j == 0) {
                        celda.setCellValue(Integer.parseInt(String.valueOf(table.getValueAt(i, j))));
                    } else {
                        if (tiempo) {
                            if (j == 1) {
                                celda.setCellValue(String.valueOf(table.getValueAt(i, j)));

                            } else {
                                String data = String.valueOf(table.getValueAt(i, j));
                                celda.setCellValue(Double.parseDouble(data));
                            }
                        } else {
                            String data = String.valueOf(table.getValueAt(i, j));
                            celda.setCellValue(Double.parseDouble(data));
                        }
                    }
                }
            }

            for (i = 0; i <= table.getColumnCount() - 1; i++) {
                hoja.autoSizeColumn(i);
            }
            createFile(libro);

        } catch (NumberFormatException Number) {
            JOptionPane.showMessageDialog(null, "No se admiten letras o caracteres especiales, solo números. \nERROR - " + Number.getMessage() + "\nRevisar fila " + (i + 1) + ", columna " + (j + 1));
        }

    }

    /**
     * Con este método se asegura que laa ventana principal siempre se iniciará
     * en el medio de la pantalla y que el tamaño se modificará conforme cambie
     * la resolución del monitor.
     *
     * @param frame JFrame que contiene la ventana principal
     * @param back JPanel que contiene la imagen de fondo de la interface de la
     * ventana principal
     */
    public void setMainWindow(JFrame frame, JPanel back) {
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setLayout(null);
        frame.setTitle("Arduino DataLogger v2.0.1 | By PanamaHitek.com");
        frame.setBounds((int) ((ancho / 2) - (ancho / 3.6622)), (int) ((alto / 2) - (alto / 2.6256)), 746, 585);

        back.setBounds(0, 0, (int) (ancho / 1.8310), (int) (alto / 1.3128));
        Imagen img = new Imagen();
        back.add(img);
        back.repaint();
        back.setLayout(null);
    }

    /**
     * Se establece la posición y tamaño de los botones en la interfaz principal
     *
     * @param b1 Botón "Ejecutar Muestreo"
     * @param b2 Botón "Acerca de"
     */
    public void setButtons(JButton b1, JButton b2) {
        int ancho = 170;
        int alto = 32;
        int x = 40;
        int y = 300;
        b1.setBounds(x, y, ancho, alto);
        b2.setBounds(x, y + 40, ancho, alto);
    }

    /**
     * Se establece la posición del JLabel que muestra la descripción de cada
     * botón en la interfaz principal
     *
     * @param label el JLabel que muestra la información
     * @param position índice que indica el botón sobre el cual se ha
     * posicionado el puntero. Dependiendo del botón se puede modificar la
     * posición del JLabel para que el mismo no se mueva de un punto en
     * específico conforme aumente o decrezca el tamaño del texto mostrado
     */
    public void setLabel(JLabel label, int position) {
        switch (position) {
            case 0:
                label.setBounds(250, 250, 446, 130);
                break;
            case 1:
                label.setBounds(250, 250, 446, 170);
                break;
            case 2:
                label.setBounds(250, 250, 446, 150);
                break;
        }
    }

    public class Imagen extends javax.swing.JPanel {

        public Imagen() {
            this.setSize(750, 560);
        }

        public void paint(Graphics grafico) {
            Dimension height = getSize();

            ImageIcon Img = new ImageIcon(getClass().getResource("/Images/ArduinoDataLoggerMain.png"));

            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(grafico);
        }
    }
}
