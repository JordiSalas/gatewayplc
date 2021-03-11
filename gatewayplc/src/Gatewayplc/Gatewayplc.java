//PASARELA PARA COMUNICACIONES PLC-MODBUS TO ITRON-SMARTCITIES
package Gatewayplc;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.*;
import java.io.IOException;
import java.util.Arrays;
/**
 * @author jsalas
 **/
public class Gatewayplc {
    
    static int Direccion_Lectura = 13000;   //Primer registro de LECTURA
    static int Direccion_Escritura = 14000; //Primer registro de ESCRITURA
    static int Numero_Registros = 6;        //Numero de registros a gestionar
    static String PLC_IP = "172.25.0.30";   //Direccion IP del PLC
    static int MB_Port = 502;               //PUERTO MODBUS
    static int [] Array_Datos = new int [Numero_Registros];//Array donde almacenamos los registros

    public static void main(String[] args) {
        // TODO code application logic here
        ModbusClient modbusClient = new ModbusClient(PLC_IP,MB_Port);
            try
            {
                //Abrimos comunicacion ModBus
		modbusClient.Connect();
                //Leemos y escribimos los valores en una sola linea
		modbusClient.WriteMultipleRegisters(Direccion_Escritura, modbusClient.ReadHoldingRegisters(Direccion_Lectura, Numero_Registros));
                //Grabamos los valores en un array
                Array_Datos = (modbusClient.ReadHoldingRegisters(Direccion_Lectura, Numero_Registros));
                //Visualizamos los valores                
		System.out.println(Arrays.toString(Array_Datos));
            }
            //Exception "Null" en caso de error
            catch (ModbusException | IOException e)
            {		
	}
    }
}
