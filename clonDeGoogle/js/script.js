async function onClickSearch( ) {
    let txtSerch = document.getElementById("txtSearch").value
    window.location.href = `./results.html?query=${txtSerch}`
}