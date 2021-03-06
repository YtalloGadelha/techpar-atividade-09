const cfg = require("../knexfile")
let env = process.env.NODE_ENV || "development"
const knex = require("knex")(cfg[env])
const express = require("express")
const morgan = require("morgan")
const bodyParser = require("body-parser")
const app = express()
const cors = require("cors")

app.use(cors())
app.use(morgan("dev"))
app.use(bodyParser.json())

app.get("/list", (req, res) => {
    knex("produto").select().then(ret => {
      res.send(ret)
    }).catch(err => {
      res.status(500).send(err)
      console.log(err)
    })
  })

app.post("/save", (req, res) => {
  const produto = req.body
  console.log(produto)
  produto.idproduto = null
  //delete produto.idproduto
  knex("produto").insert(produto, "idproduto").then(ret => {
      console.log(ret)
      res.send(ret)
  }).catch(err => {
    res.status(500).send(err)
    console.log(err)
  })
})

app.put("/save", (req, res) => {
  const produto = req.body
  knex("produto").where("idproduto", produto.idproduto).update(produto).then(ret => {
      res.send(ret)
  }).catch(err => {
    res.status(500).send(err)
    console.log(err)
  })
})

knex.migrate.latest().then(_ =>
  app.listen(3000, _ =>
console.log("Servidor online!")))
