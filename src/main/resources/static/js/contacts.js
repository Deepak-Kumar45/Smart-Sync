
console.log("This is user contacts page");
async function setContactInModal(id) {
    let raw = await fetch("http://localhost:8085/api/contacts/" + id);
    let contact = await raw.json();
    console.log(contact);
    document.querySelector("#contact-image").setAttribute("src", contact["contactImageUrl"]);
    document.querySelector("#contact-name").innerHTML = contact["contactDtoName"];
    document.querySelector("#contact-mail").innerHTML = contact["contactDtoEmail"];
    document.querySelector("#contact-address").innerHTML = contact["contactAddress"];
    document.querySelector("#contact-favourite").innerHTML = contact["isFavourite"];
    document.querySelector("#contact-mobile").innerHTML = contact["contactDtoMobile"];
    document.querySelector("#facebook-link").setAttribute("href", contact["linkedIn"]);
    document.querySelector("#twitter-link").setAttribute("href", contact["http://www.google.com"]);
}

function deleteContact(contactId) {
    document.querySelector("#confirmDelete").setAttribute("href","delete-contact?id="+contactId);
}