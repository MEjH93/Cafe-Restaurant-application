package com.comtrade.controllerBL;

import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.systemOperationRestaurantTable.ReturnRestaurantTable;
import com.comtrade.transfer.TransferClass;

public class ControllerRestaurant implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
          //  case GET_RESTAURANT:
               // genericSystemOperation = new GetRestaurantService();
             //   break;
          //  case GET_RESTAURANTS:
           //     genericSystemOperation = new GetRestaurantsService();
           //     break;
            case RETURN_RESTAURANT_TABLE:
                genericSystemOperation = new ReturnRestaurantTable();
                break;
         //   case RETURN_AVAILABLE_TABLES:
             //   genericSystemOperation = new ReturnAvailableTablesService();
            //    break;
           // case PUT:
              //  genericSystemOperation = new SaveNewRestaurantService();
             //   break;


            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }
}
