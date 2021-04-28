package com.aladdindb.util;

import java.time.LocalDateTime;

/**
 *
 * @author Macit Kandemir
 */
public class IdHandler {
    
    public String createHexID( ) {

        var t = LocalDateTime.now();
        
        var sb = new StringBuilder();
        
        sb.append( Hex.create2CharHex   ( t.getYear         () - 2000) );
        sb.append( Integer.toHexString  ( t.getMonthValue   () ) );
        sb.append( Hex.create2CharHex   ( t.getDayOfMonth   () ) );
        sb.append( Hex.create2CharHex   ( t.getHour         () ) );
        sb.append( Hex.create2CharHex   ( t.getMinute       () ) );
        sb.append( Hex.create2CharHex   ( t.getSecond       () ) );
        sb.append( Hex.createXCharHex   ( 8, t.getNano      () ) );
        
        return sb.toString();
    }
    
    public LocalDateTime createDate( String hexID ) {
        
        var year    = Integer.decode( "#" + hexID.substring( 0, 2  ) );
        var month   = Integer.decode( "#" + hexID.substring( 2, 3  ) );
        var days    = Integer.decode( "#" + hexID.substring( 3, 5  ) );
        var hours   = Integer.decode( "#" + hexID.substring( 5, 7  ) );
        var min     = Integer.decode( "#" + hexID.substring( 7, 9  ) );
        var sec     = Integer.decode( "#" + hexID.substring( 9, 11 ) );
        
        return LocalDateTime.of( year+2000, month, days, hours, min, sec );
        
    }
    
}
