INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'livro') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'livro'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Vestuário') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Vestuário'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Automotivo') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Automotivo'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Locação de imóveis') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Locação de imóveis'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Artigos domésticos') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Artigos domésticos'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Artigos para animais de estimação') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Artigos para animais de estimação'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Artigos para escritório') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Artigos para escritório'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Brinquedos e jogos') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Brinquedos e jogos'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Classificados') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Classificados'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Eletrônicos') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Eletrônicos'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Entretenimento Adulto') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Entretenimento Adulto'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Familia') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Familia'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Ferramentas') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Ferramentas'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Hobbies') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Hobbies'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Instrumentos musicais') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Instrumentos musicais'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Itens gratuitos') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Itens gratuitos'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Jardim e ambientes externos') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Jardim e ambientes externos'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Suprimentos para reforma residencial') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Suprimentos para reforma residencial'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Venda de imóveis residenciais') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Venda de imóveis residenciais'
) LIMIT 1;

INSERT INTO departamento (nome)
SELECT * FROM (SELECT 'Vestuário') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM departamento WHERE nome = 'Vestuário'
) LIMIT 1;


INSERT INTO status (nome)
SELECT * FROM (SELECT 'Em processo') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM status WHERE nome = 'Em processo'
) LIMIT 1;

INSERT INTO status (nome)
SELECT * FROM (SELECT 'Aceito') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM status WHERE nome = 'Aceito'
) LIMIT 1;

INSERT INTO status (nome)
SELECT * FROM (SELECT 'Cancelado') AS tmp WHERE NOT EXISTS (
    SELECT nome FROM status WHERE nome = 'Cancelado'
) LIMIT 1;
