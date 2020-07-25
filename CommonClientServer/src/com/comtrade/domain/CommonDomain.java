package com.comtrade.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface CommonDomain {

    String returnTableName();
    String saveReturnQuestionMark();
    String saveReturnColums();
    
    PreparedStatement setInsertValues(PreparedStatement preparedStatement);

    Object readObjects(ResultSet resultSet);

    String returnIDQuestionMark();

    String idColumnName();
    
    String return1QuestionMark();

    PreparedStatement returnIDPreparedStatement(PreparedStatement preparedStatement);

    String returnColumnforDelete();

    PreparedStatement returnDeletePreparedStatement(PreparedStatement preparedStatement);
    
    String commonColumns();
	String familiarColumns();
	String returnIDQuestionMarks();
	String saveReturnQuestionMarks();
    
    
}