ajax = new XMLHttpRequest();
edit = false;

function call(str) {
	ajax.abort();
	ajax.open("GET", "/ajax?id=" + str + "&rand=" + Math.random(), true);
	ajax.send();
}

function status_check() {
	if ((ajax.readyState == 4) && (ajax.status == 200)) {
		eval(ajax.responseText.trim());
	}
}

function btn(id) {
	if (edit) {
		location.href = "/edit?id=" + id;
	} else {
		call(id);
	}
}

ajax.onreadystatechange = status_check;
