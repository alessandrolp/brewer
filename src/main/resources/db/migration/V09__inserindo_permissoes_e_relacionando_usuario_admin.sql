INSERT INTO permissao (id, nome) VALUES (1, 'ROLE_CADASTRO_USUARIO');
INSERT INTO permissao (id, nome) VALUES (2, 'ROLE_CADASTRO_CIDADE');

INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 2);

INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (
	(SELECT id FROM usuario WHERE email = 'admin@brewer.com.br'), 1);