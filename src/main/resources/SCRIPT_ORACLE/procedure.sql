


create or replace NONEDITIONABLE PROCEDURE		productoprocedure(
	V_ID_PROD			IN 		NUMBER,
	V_NOMBRE			IN 		VARCHAR2,
	V_FEC_REGISTRO		IN 		DATE,
	o_cursor			OUT 	SYS_REFCURSOR,
	o_sqlcode			OUT 	NUMBER,
	o_sqlmsg			OUT 	VARCHAR2)
AS
BEGIN

INSERT INTO SYSTEM.TBL_PRODUCTO (ID_PROD, NOMBRE, FEC_REGISTRO) Values (V_ID_PROD, V_NOMBRE, V_FEC_REGISTRO);


	o_sqlcode:=0;
	o_sqlmsg:='sucess';
open c_cursor for
		select ID_PROD, NOMBRE, FEC_REGISTRO from SYSTEM.TBL_PRODUCTO;


EXCEPTION
	WHEN OTHERS THEN
	ROLLBACK;
	o_sqlcode := SQLCODE;
	o_sqlmsg:= 'could not get product list ';
	DBMS_OUTPUT.PUT_LINE('CUS - Error ' ||o_sqlcode||' - '||o_sqlmsg);


END;