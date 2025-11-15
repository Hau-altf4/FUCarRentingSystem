// Đảm bảo tên package khớp với vị trí bạn vừa tạo
package com.TN.carrental.model.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Tạo đối tượng Configuration
            Configuration configuration = new Configuration();
            
            // Dòng này sẽ tự động đọc file 'hibernate.cfg.xml' 
            // từ 'src/main/resources'
            configuration.configure(); 
            
            // Xây dựng ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
            
            // Xây dựng SessionFactory từ ServiceRegistry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    // Phương thức public để các lớp khác gọi và lấy SessionFactory
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }
    
    // (Tùy chọn) Phương thức để đóng SessionFactory khi ứng dụng tắt
    public static void shutdown() {
        if (sessionFactory != null) {
            getSessionFactory().close();
        }
    }
}