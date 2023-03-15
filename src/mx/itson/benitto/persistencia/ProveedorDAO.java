/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benitto.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benitto.entidades.Proveedor;
import mx.itson.benitto.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author egarz
 */
public class ProveedorDAO {
    
    public static List<Proveedor> obtenerTodos(){
        List<Proveedor> Proveedor = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Proveedor> criteriaQuery  = session.getCriteriaBuilder().createQuery(Proveedor.class);
            criteriaQuery.from(Proveedor.class);
            
            Proveedor = session.createQuery(criteriaQuery).getResultList();

        }catch(Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
        }
             return Proveedor;   
    }
    
       public static boolean guardar (String nombre, String clave, String contacto, String telefono, String correo, String direccion){
   
        boolean resultado = false;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Proveedor p = new Proveedor();
            p.setNombre(nombre);
            p.setClave(clave);
            p.setContacto(contacto);
            p.setTelefono(telefono);
            p.setCorreo(correo);
            p.setDireccion(direccion);
         
         
            
            session.save(p);        
            session.getTransaction().commit();
            
            resultado = p.getId() !=0;
            
        }catch (Exception ex){
            System.err.println("Ocurrio un error" + ex.getMessage());
            
        }
        return resultado;
        
    }
     
      public static Proveedor obtenerPorId(int id ){
        Proveedor proveedor = null;
        try {
             Session session = HibernateUtil.getSessionFactory().openSession();
             proveedor = session.get(Proveedor.class, id);
             
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error " + ex.getMessage());
        
            
        }
        return proveedor;
    }
     
         
     public  static boolean editar ( int id, String nombre, String clave, String contacto, String telefono, String correo, String direccion){
        boolean resultado  = false;
    try {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Proveedor proveedor = obtenerPorId(id);
    if(proveedor != null){      
     proveedor.setNombre(nombre);
     proveedor.setClave(clave);
     proveedor.setContacto(contacto);
     proveedor.setTelefono(telefono);
     proveedor.setCorreo(correo);
     proveedor.setDireccion(direccion);
        
        session.saveOrUpdate(proveedor);
        session.getTransaction().commit();
        resultado = true;
    }
    
    
            
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
           
        }
      return resultado;
}
     
     
     
     
      public boolean eliminar (int id ){
    
    boolean resultado  = false;
    
     try {
          Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Proveedor proveedor = obtenerPorId(id);
    if(proveedor != null){
        session.delete(proveedor);
        session.getTransaction().commit();
        resultado = true;
    }
         
         
         
         
         } catch (HibernateException ex) {
            System.err.println("Ocurrio un erro " + ex.getMessage());
        
           
        }
      return resultado;
     }
    
    
}
