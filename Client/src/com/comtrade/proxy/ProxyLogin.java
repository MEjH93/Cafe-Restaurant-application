package com.comtrade.proxy;

import com.comtrade.domain.User;
import com.comtrade.view.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class ProxyLogin implements Proxy {
    private User user;



    @Override
    public void login(User user) throws IOException, InvocationTargetException, InterruptedException, ClassNotFoundException {
    	Set<String> setRoleName = user.getSetRoleUser().stream().map(rola -> rola.getRole_name()).collect(Collectors.toSet());

        if (setRoleName.contains("owner")) {
            OwnerForm ownerForm = new OwnerForm(user);
            ownerForm.setVisible(true);

        } else if (setRoleName.contains("waiter") ){
            WaiterForm waiterEntryForm = new WaiterForm(user);
            waiterEntryForm.setVisible(true);

        }else if (setRoleName.contains("manager")){
        	JOptionPane.showMessageDialog(null, "Manager form is still in progress, donate on paypal in order for it to be finished,Paypal address is -> mamutinaostrvu@co.ya");
            ManagerForm managerForm = new ManagerForm(user);
            managerForm.setVisible(false);
        }


    }
}