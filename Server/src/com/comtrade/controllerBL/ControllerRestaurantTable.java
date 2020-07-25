package com.comtrade.controllerBL;

import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.systemOperationRestaurantTable.ReturnRestaurantTable;
import com.comtrade.systemOperationRestaurantTable.ReturnRestaurantTableNumbers;
import com.comtrade.systemOperationRestaurantTable.SaveRestaurantTable;
import com.comtrade.transfer.TransferClass;

public class ControllerRestaurantTable implements CommandBase {

	@Override
	  public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
            case RETURN_RESTAURANT_TABLE:
                genericSystemOperation = new ReturnRestaurantTable();
                break;
            case POST:
            	genericSystemOperation = new SaveRestaurantTable();
                break;
            case GET:
            	genericSystemOperation = new ReturnRestaurantTableNumbers();
                break;
                
            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }

}
