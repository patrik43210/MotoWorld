
    $('#loadAuthors').click(() => {
    $('.authors-container').empty();

    fetch('http://localhost:8080/users').
    then((response) => response.json()).
    then((json) => json.forEach((user, idx) => {
    console.log(user.username);

    let tableRow = '<tr>' +
    '<td >'+
    '<a class="float-left text-dark mb-3 ml-5 center bg-success text-white p-3 rounded" href="/profile/' + user.username + '">'+user.username+'</a>' +
    '</td>' +
    '</tr>'
    $('.authors-container').append(tableRow);
}))
});

    $('#hideAuthors').click(() => {
    $('.authors-container').empty();
});

