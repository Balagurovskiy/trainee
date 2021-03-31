CREATE PROCEDURE get_customer_history(IN userId INT)
	SELECT orders.id, orders.name, orders.price, orders.currency, orders.processed
	FROM orders
	WHERE userId = orders.userId;
	
-- CALL get_customer_history(1,1);
-- SHOW PROCEDURE STATUS;
-- SHOW PROCEDURE STATUS WHERE Db = 'shop';
-- DROP PROCEDURE IF EXISTS get_customer_history;