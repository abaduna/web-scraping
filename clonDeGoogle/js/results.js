const URL_API = `http://localhost:8080/api/`
let jsonExsample =[
    {
        "id": 1,
        "domain": "google.com",
        "url": "https://www.google.com/",
        "title": "google",
        "description": "google",
        "picture": null,
        "ranck": 1
    },
    {
        "id": 363,
        "domain": "elpais.com",
        "url": "https://elpais.com/elviajero/",
        "title": "El Viajero en EL PAÍS",
        "description": "Noticias sobre viajes, destinos turísticos en España y el mundo e información útil para vacaciones y escapadas",
        "picture": "https://imagenes.elpais.com/resizer/q1Bzvi_vytH6svtum46Q9iiKu8Q=/300x300/arc-anglerfish-eu-central-1-prod-prisa.s3.amazonaws.com/public/PSMME3J7ZBDOHK4S6ZIIFVDEAQ.png",
        "ranck": null
    }
    
]

 async function serch(query ) {
    console.log("serch");
    let url = URL_API + `search?query=${query}`;
    let result = await fetch(url)
    let json = await result.json()
    let jsonResult = json;
    let htmlFinal = "";
    for (let jsonElement of jsonResult){
        htmlFinal += ` <div class="result">
        <div class="title">
          <img class="picture"
            src="${jsonElement.picture}" />
          <a href="${jsonElement.url}" target:"_blanck">${jsonElement.title}</a>
        </div>
        <div class="description">
          <p>${jsonElement.description}</p>
        </div>
      </div>
`
    }
    console.log('htmlFinal', htmlFinal)
    document.getElementById("result").innerHTML = htmlFinal;
   
}
async function onClickSearch( ) {
    let txtSerch = document.getElementById("txtSearch").value
    serch(txtSerch)
}
function load( ) {
   let buscar = window.location.href.split("?query=")[1]
   console.log('buscar', buscar)
   serch(buscar)
   document.getElementById("txtSearch").value = buscar
}
document.addEventListener("DOMContentLoaded", load);