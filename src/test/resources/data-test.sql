DELETE FROM TODO
WHERE ID BETWEEN 1001 AND 9000;
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        1001,
        'testtitle',
        'testnote'
    );
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        2001,
        '2testtitle',
        '2testnote'
    );
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        3001,
        '3testtitle',
        '3testnote'
    );
INSERT INTO TODO(ID, TITLE, NOTE)
VALUES(
        4001,
        '4testtitle',
        '4testnote'
    );