const produtos = [

    {idproduto: 1, nomeproduto: "Arroz", precoproduto:"R$10,00", descricaoproduto: "Integral"},
    {idproduto: 2, nomeproduto: "Macarrão", precoproduto:"R$12,00", descricaoproduto: "Integral"},
    {idproduto: 3, nomeproduto: "Feijão", precoproduto:"R$14,00", descricaoproduto: "Corda"},
    {idproduto: 4, nomeproduto: "Bolo", precoproduto:"R$20,00", descricaoproduto: "Confeitado"},
    {idproduto: 5, nomeproduto: "Carne", precoproduto:"R$30,00", descricaoproduto: "Picanha"},
    {idproduto: 6, nomeproduto: "Frango", precoproduto:"R$34,00", descricaoproduto: "Peito"},
    {idproduto: 7, nomeproduto: "PES 2018", precoproduto:"R$100,00", descricaoproduto: "Jogo PS4"},
    {idproduto: 8, nomeproduto: "FIFA 2018", precoproduto:"R$200,00", descricaoproduto: "Jogo XBox one"}
]  

exports.up = knex => knex("produto").insert(produtos)

exports.down = knex => knex("produto").del()
  .whereIn("idproduto", produtos.map(e => e.idproduto))