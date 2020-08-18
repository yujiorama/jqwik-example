DELETE FROM TODO
WHERE ID IN (1, 2);
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        1,
        'todo 1 title',
        'todo 1 note'
    );
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        2,
        'todo 2 title',
        'todo 2 note'
    );