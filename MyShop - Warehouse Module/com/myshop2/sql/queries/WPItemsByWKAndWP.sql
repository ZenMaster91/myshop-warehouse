SELECT 
    p.corridor,
    p.position,
    p.height,
    p.side,
    p.product_id,
    wp.wp_id,
    wp.date_created,
    p.stock,
    p.name,
    p.description AS 'p_desc',
    p.weight,
    p.price,
    o.order_id,
    oi.items_collected,
    p.company_price,
    p.category,
    i.incidence_id,
    i.description AS 'i_desc',
    i.solved,
    mb.mail_box_id,
    oi.order_item_id,
    s.name AS 'status',
    oi.quantity,
    oi.collected
FROM
    myshop.working_plan AS wp
        LEFT JOIN
    myshop.order_item AS oi ON wp.wp_id = oi.working_plan_id
        LEFT JOIN
    myshop.order AS o ON oi.order_id = o.order_id
        LEFT JOIN
    myshop.status AS s ON s.status_id = o.status_id
        LEFT JOIN
    myshop.full_products AS p ON oi.product_id = p.product_id
        LEFT JOIN
    myshop.incidence AS i ON wp.incidence_id = i.incidence_id
        LEFT JOIN
    myshop.mail_box AS mb ON oi.order_item_id = mb.order_item_id
WHERE
    wk_id = :id AND collected = 0
ORDER BY wp.wp_id