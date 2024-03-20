# EasyPay App

O EasyPay é um aplicativo de exemplo desenvolvido para demonstrar uma integração básica com a plataforma de pagamentos da LIO. Ele permite que os usuários realizem pagamentos por meio de diferentes métodos, como cartão de crédito à vista, cartão de crédito parcelado e cartão de débito.

## Funcionalidades Principais

1. **Seleção de Produtos:** Os usuários podem visualizar uma lista de produtos disponíveis para compra no aplicativo.

2. **Adição ao Carrinho:** Eles podem adicionar produtos ao carrinho de compras clicando neles.

3. **Visualização do Carrinho:** Os usuários podem visualizar os produtos selecionados e o total no carrinho de compras.

4. **Pagamento:** Ao clicar no botão "Pagar", os usuários são direcionados para a tela de pagamento, onde podem escolher o método de pagamento e o número de parcelas, se aplicável.

5. **Processamento do Pagamento:** Após confirmar o pagamento, o aplicativo envia os detalhes da transação para a LIO, que processa o pagamento e fornece uma resposta ao aplicativo.

6. **Feedback ao Usuário:** O aplicativo exibe mensagens de feedback ao usuário com base no resultado da transação, informando se o pagamento foi bem-sucedido ou se houve algum erro.

## Pré-requisitos

Antes de executar o aplicativo, certifique-se de ter as seguintes coisas configuradas:

- Credenciais válidas da LIO para integração com a plataforma de pagamentos.
- SDK da LIO integrado ao projeto do aplicativo.
- Permissões adequadas configuradas no arquivo AndroidManifest.xml.
- Dispositivo Android com acesso à Internet para realizar transações.

## Como Executar

1. Clone o repositório do EasyPay App para o seu ambiente de desenvolvimento.
2. Abra o projeto no Android Studio.
3. Compile e execute o aplicativo em um dispositivo Android ou emulador.
4. Explore as diferentes funcionalidades do aplicativo, incluindo a seleção de produtos, adição ao carrinho e processamento de pagamentos.

## Observações

- Este aplicativo foi desenvolvido apenas para fins de demonstração e não deve ser usado para realizar transações reais.
- Certifique-se de testar o aplicativo em um ambiente de sandbox antes de fazer qualquer integração com um ambiente de produção.
- Sempre siga as práticas recomendadas ao lidar com informações financeiras e dados sensíveis dos usuários.

Para mais informações sobre a integração com a plataforma de pagamentos da LIO, consulte a documentação oficial da LIO.

