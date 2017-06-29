DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllBBOfStock`(in argTicket varchar(45), argPeriod int, argSD int, argType varchar(45))
BEGIN
	SELECT i.* FROM indicator_bb i 
    inner join stock s on s.id = i.STOCK_ID 
    inner join period p on p.id = i.PERIOD_ID 
    where s.ticket = upper(argTicket) and standardDeviation = argSD and p.value = argPeriod and type = argType order by str_to_date(s.open_date, '%m/%d/%Y');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllEMAOfStock`(in argTicket varchar(45), in argPeriod int, in argType varchar(45))
BEGIN
	select i.* from indicator_ema i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p on p.id = i.PERIOD_ID
	where type = argType and s.ticket = upper(argTicket) and p.VALUE = argPeriod 
    order by str_to_date(s.open_date, '%m/%d/%Y') ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMACDOfStock`(in argTicket varchar(45), in argPeriodX int, in argPeriodY int, in argPeriodSignal int, in argType varchar(45))
BEGIN
	select i.* from indicator_macd i 
    inner join period p_x on p_x.id = i.PERIOD_X_ID 
    inner join period p_y on p_y.id = i.PERIOD_Y_ID 
    inner join period p_signal on p_signal.id = i.PERIOD_SIGNAL_ID 
    inner join stock s on s.id = i.STOCK_ID 
    where s.ticket = upper(argTicket) and i.type = argType and p_x.value = argPeriodX and p_y.value = argPeriodY and p_signal.value = argPeriodSignal 
    order by str_to_date(s.open_date, '%m/%d/%Y');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllRSIOfStock`(in argTicket varchar(45), in argPeriod int, in argType varchar(45))
BEGIN
	select i.* from indicator_rsi i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p on p.id = i.PERIOD_ID
	where type = argType and s.ticket = upper(argTicket) and p.VALUE = argPeriod 
    order by str_to_date(s.open_date, '%m/%d/%Y') ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllSMAOfStock`(in argTicket varchar(45), in argPeriod int, in argType varchar(45))
BEGIN
	select i.* from indicator_sma i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p on p.id = i.PERIOD_ID
	where type = argType and s.ticket = upper(argTicket) and p.VALUE = argPeriod 
    order by str_to_date(s.open_date, '%m/%d/%Y') ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllStochRSIOfStock`(in argTicket varchar(45), in argPeriodRsi int, in argPeriodStochRsi int, in argPeriodSk int, in argPeriodSd int,  in argType varchar(45))
BEGIN
	select i.* from indicator_stoch_rsi i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p_rsi on p_rsi.id = i.PERIOD_RSI_ID 
    inner join period p_stoch_rsi on p_stoch_rsi.id = i.PERIOD_STOCH_RSI_ID 
    inner join period p_sk on p_sk.id = i.PERIOD_SK 
    inner join period p_sd on p_sd.id = i.PERIOD_SD 
	where type = argType and s.ticket = upper(argTicket) 
    and p_rsi.VALUE = argPeriodRsi 
    and p_stoch_rsi.VALUE = argPeriodStochRsi 
    and p_sk.value = argPeriodSk  
    and p_sd.value = argPeriodSd 
    order by str_to_date(s.open_date, '%m/%d/%Y') ;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllStockByTicketName`(in argTicket varchar(45), in argNumberOfDay int)
BEGIN
	select * from stock where ticket = upper(argTicket) 
    and case when argNumberOfDay = -1 then true else weekday(str_to_date(open_date, '%m/%d/%Y')) = argNumberOfDay end 
    order by str_to_date(open_date, '%m/%d/%Y');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBBAtOneDay`(in argTicket varchar(45), in argPeriod int, in argType varchar(45), in argOpenDate varchar(45), in argSD int)
BEGIN
	select i.* from indicator_bb i 
	inner join stock s on s.id = i.STOCK_ID 
	inner join period p  on p.id = i.PERIOD_ID 
	where s.ticket = upper(argTicket) 
    and p.value =  argPeriod 
    and i.type = argType 
    and i.standardDeviation = argSD 
    and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(argOpenDate, '%m/%d/%Y') 
    and case when argType = 'WEEKLY' then weekday(str_to_date(s.open_date, '%m/%d/%Y')) = 4 else true end 
	order by str_to_date(s.open_date, '%m/%d/%Y') desc limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEMAAtOneDay`(in argTicket varchar(45), in argType varchar(45), in argPeriod int, in argOpenDay varchar(45))
BEGIN
	SELECT i.* from indicator_ema i 
    inner join stock s on s.id = i.STOCK_ID 
    inner join period p on p.id = i.PERIOD_ID 
    where s.ticket = upper(argTicket) 
    and p.value = argPeriod 
    and i.type = argType 
    and str_to_date(s.open_date, '%m/%d/%Y') <= str_to_date(argOpenDay, '%m/%d/%Y') 
    and case when argType = 'WEEKLY' then weekday(str_to_date(s.open_date, '%m/%d/%Y')) = 4 else true end 
    order by str_to_date(s.open_date, '%m/%d/%Y') desc limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMACDAtOneDay`(in argTicket varchar(45) , in argPeriodX int, in argPeriodY int, in argPeriodSignal int,  in argOpenDay varchar(45), in argType varchar(45))
BEGIN
	SELECT i.* from indicator_macd i 
    inner join stock s on s.id = i.STOCK_ID 
    inner join period p_x on p_x.id = i.PERIOD_X_ID 
    inner join period p_y on p_y.id = i.PERIOD_Y_ID 
    inner join period p_signal on p_signal.id = i.PERIOD_SIGNAL_ID 
    where s.ticket = upper(argTicket) 
    and p_x.value = argPeriodX 
    and p_y.value = argPeriodY 
    and p_signal.value = argPeriodSignal  
    and i.type = argType 
    and str_to_date(s.open_date, '%m/%d/%Y') <= str_to_date(argOpenDay, '%m/%d/%Y') 
    and case when argType = 'WEEKLY' then weekday(str_to_date(s.open_date, '%m/%d/%Y')) = 4 else true end 
    order by str_to_date(s.open_date, '%m/%d/%Y') desc limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getRSIAtOneDay`(in argTicket varchar(45), in argType varchar(45), in argPeriod int, in argOpenDay varchar(45))
BEGIN
	SELECT i.* from indicator_rsi i 
    inner join stock s on s.id = i.STOCK_ID 
    inner join period p on p.id = i.PERIOD_ID 
    where s.ticket = upper(argTicket) 
    and p.value = argPeriod 
    and i.type = argType 
    and str_to_date(s.open_date, '%m/%d/%Y') <= str_to_date(argOpenDay, '%m/%d/%Y') 
    and case when argType = 'WEEKLY' then weekday(str_to_date(s.open_date, '%m/%d/%Y')) = 4 else true end 
    order by str_to_date(s.open_date, '%m/%d/%Y') desc limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSMAAtOneDay`(in argTicket varchar(45), in argPeriod int, in argType varchar(45), in argOpenDate varchar(45))
BEGIN
	select i.* from indicator_sma i 
	inner join stock s on s.id = i.STOCK_ID 
	inner join period p  on p.id = i.PERIOD_ID 
	where s.ticket = upper(argTicket) 
    and p.value =  argPeriod 
    and i.type = argType 
    and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(argOpenDate, '%m/%d/%Y') 
    and case when argType = 'WEEKLY' then weekday(str_to_date(s.open_date, '%m/%d/%Y')) = 4 else true end 
	order by str_to_date(s.open_date, '%m/%d/%Y') desc limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStochRSIAtOneDay`(in argTicket varchar(45), in argPeriodRsi int, in argPeriodStochRsi int, in argPeriodSk int, in argPeriodSd int,  in argType varchar(45), in argDate varchar(45))
BEGIN
	select i.* from indicator_stoch_rsi i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p_rsi on p_rsi.id = i.PERIOD_RSI_ID 
    inner join period p_stoch_rsi on p_stoch_rsi.id = i.PERIOD_STOCH_RSI_ID 
    inner join period p_sk on p_sk.id = i.PERIOD_SK 
    inner join period p_sd on p_sd.id = i.PERIOD_SD 
	where i.type = argType 
    and s.ticket = upper(argTicket) 
    and p_rsi.VALUE = argPeriodRsi 
    and p_stoch_rsi.VALUE = argPeriodStochRsi 
    and p_sk.value = argPeriodSk 
    and p_sd.value = argPeriodSd 
    and str_to_date(s.open_date, '%m/%d/%Y') <= str_to_date(argDate, '%m/%d/%Y') 
    order by str_to_date(s.open_date, '%m/%d/%Y')  desc 
    limit 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStockAllStockByTicketAndDate`(in argTicket varchar(45), in argOpenDate varchar(45), in argNumberOfDay int, in argLimit int)
BEGIN
	select * from stock where ticket = upper(argTicket) 
	and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(argOpenDate, '%m/%d/%Y') 
    and case when argNumberOfDay = -1 then true else weekday(str_to_date(open_date, '%m/%d/%Y')) = argNumberOfDay end 
    order by str_to_date(open_date, '%m/%d/%Y') desc
    limit argLimit;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStockAllStockToDate`(in argTicket varchar(45), in argOpenDate varchar(45), in argNumberOfDay int)
BEGIN
	select * from stock where ticket = upper(argTicket) 
	and str_to_date(open_date, '%m/%d/%Y') <= str_to_date(argOpenDate, '%m/%d/%Y') 
    and case when argNumberOfDay = -1 then true else weekday(str_to_date(open_date, '%m/%d/%Y')) = argNumberOfDay end 
    order by str_to_date(open_date, '%m/%d/%Y');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllADXOfStock`(in argTicket varchar(45), in argPeriod int, in argType varchar(45))
BEGIN
	select i.* from indicator_adx i
	inner join stock s on s.id = i.STOCK_ID
	inner join period p on p.id = i.PERIOD_ID
	where type = argType and s.ticket = upper(argTicket) and p.VALUE = argPeriod 
    order by str_to_date(s.open_date, '%m/%d/%Y') ;
END$$
DELIMITER ;

