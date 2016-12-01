SELECT DISTINCT
    *
FROM
    myshop.full_order AS o
        LEFT JOIN
    myshop.order AS ord ON o.order_id = ord.order_id
        LEFT JOIN
    myshop.full_products AS p ON o.product_id = p.product_id
        LEFT JOIN
    myshop.full_customers AS c ON o.customer_id = c.customer_id
        LEFT JOIN
    myshop.order_item AS oi ON o.order_id = oi.order_id
        AND o.product_id = oi.product_id
        LEFT JOIN
    myshop.mail_box AS mb ON mb.mail_box_id = mail_box
        LEFT JOIN
    myshop.shipment AS s ON mb.shipment_id = s.shipment_id
WHERE
    (ord.status_id = 4 OR ord.status_id = 5) AND s.sent=0
ORDER BY o.date_received