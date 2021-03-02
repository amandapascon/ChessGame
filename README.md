# ChessGame

# Instruções para o jogo
Para jogar esse jogo, os jogadores devem, após executarem o projeto em um terminal, ou executarem o programa do próprio terminal do NetBeans, seguir as instruções fornecidas:
    
Escolher se deseja iniciar um novo jogo ou carregar um jogo já existente
Caso deseje carregar um jogo existente, o jogador deve digitar o mesmo nome que escreveu ao salvá-lo, nesse exemplo, estou tentando carregar um jogo que salvei como teste:

Caso deseje iniciar um novo jogo:
O jogador 1 deve digitar seu nome (o nome por definição deverá conter no mínimo 3 caracteres)
O Jogador 1 deve escolher entre 1 e 2 para selecionar a cor das peças que deseja
O jogador 2 deve digitar seu nome (a regra dos 3 caracteres também se aplica aqui)
A cor das peças do Jogador 2 será informada automaticamente.

O jogo exibirá em tela o nome dos jogadores, suas peças ativas e capturadas e o tabuleiro com a posição atual das peças, caso em 1. tenha se escolhido a., isso corresponde a essa exibição de acordo com o estado onde o jogo foi salvo, caso b., corresponde ao tabuleiro com as posições iniciais padrão de um jogo, sendo a vez do jogador que escolheu as peças brancas.
Caso escolha a, exibimos o tabuleiro com o estado no qual o jogo foi salvo:

Caso escolha b, um novo jogo será iniciado:

Detalha que, quando um jogo não é achado, inicia-se um novo automaticamente:

O Jogador que tem a vez deve informar ao jogo se deseja sair, com o mesmo tipo de seleção de antes: 1 e 2

Caso escolha 1, ele poderá escolher entre apenas sair ou se deseja salvar o jogo:

Se optar por sair, o jogo é encerrado

Caso contrário, o jogador escolhe um nome para salvar o progresso do jogo, este deverá ser o nome usado em 1.a. para carregar um jogo, em seguida o jogo é encerrado:

Caso escolha 2, ele prossegue normalmente o jogo. Isso será perguntado a cada jogada.

O Jogador que tem a vez deve digitar linha e coluna de origem do movimento desejado separadas por espaço e usando MAIÚSCULAS para que as coordenadas sejam reconhecidas pelo programa, qualquer coisa diferente disso será dado como entrada inválida, tendo o jogador que digitá-las novamente. 
Em seguida, deve seguir o mesmo procedimento ao digitar linha e coluna de destino desejadas.

O jogo irá analisar se o movimento solicitado é válido
Caso for, o jogo realiza o movimento e mostra em tela novamente o nome dos jogadores, as peças ativas e capturadas respectivas e o tabuleiro com o novo estado atual, passando a vez para o outro jogador, nesse caso, a Lívia fez um movimento válido, portanto a peça foi movimentada e a vez passada para Amanda.

Caso não for, o jogo faz a pergunta de salvar o jogo ou não, e avisa a tentativa inválida de movimento e não passa a vez, solicitando que o mesmo jogador tente realizar novamente sua jogada.

Quando uma peça é capturada, a lista com as suas peças será incrementada e a de peças ativas, decrementada, para melhor visualização de seu jogo

O jogo segue assim, avisando de possíveis estados, como xeque.
Caso seu Rei se encontre em xeque, o jogo irá avisá-lo, você deve então, obrigatoriamente, realizar um movimento que desfaça o xeque, caso contrário, o movimento não será válido

Em caso de xeque mate, o jogo irá avisá-lo, e em seguida, irá encerrar o jogo.

