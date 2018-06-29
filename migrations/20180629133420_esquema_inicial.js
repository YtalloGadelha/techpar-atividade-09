
exports.up = knex => knex.schema.createTable("produto", tb => {
    tb.increments("idproduto")
    tb.string("nomeproduto").notNullable()
    tb.string("precoproduto").notNullable()
    tb.string("descricaoproduto").notNullable()
  })
  
  exports.down = function(knex, Promise) {
    return knex.schema.dropTable("produto")
  };