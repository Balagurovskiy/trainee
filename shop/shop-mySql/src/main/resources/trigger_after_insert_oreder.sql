create trigger trigger_after_insert_oreder after insert on shop.orders
	for each row
		insert into shop.order_info(orderUpdated, orderId, processed) values (CURDATE(), new.id, new.processed)


 create trigger trigger_after_update_oreder after update on shop.orders
 	for each row
 		insert into shop.order_info(orderUpdated, orderId, processed) values (CURDATE(), new.id, new.processed);

create trigger trigger_after_delete_oreder after update on shop.orders
 	for each row
		DELETE FROM shop.order_info WHERE oredrId=new.id
 		
 		
-- show triggers;
-- DROP TRIGGER IF EXISTS shop.trigger_after_insert_oreder;