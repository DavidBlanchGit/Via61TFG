import pytest
from fastapi.testclient import TestClient
from sqlalchemy.orm import Session
from main import app, get_db
from tablas.models import Model_User

# Usuario usado de prueba
datos_usuario = {
    "nmro_id": 48,
    "txto_protonmail": "usuario1@ejemplo.com",
    "txto_psswrd": "contraseña1",
    "foto_perfil": "ruta/imagen1.jpg",
    "txto_nick": "usuario1",
    "cdgo_rango": 0
}


@pytest.fixture(scope="module")
def db():
    from main import get_db
    db = get_db()
    yield db


@pytest.fixture(scope="module")
def test_client():
    with TestClient(app) as client:
        yield client

# Testing de la funcion /getUsers
def test_obtener_usuarios(db: Session, test_client: TestClient):
    response = test_client.get("/getUsers")
    assert response.status_code == 200
    assert isinstance(response.json(), list)
    assert len(response.json()) == 1

    assert response.json()[0]["nmro_id"] == datos_usuario["nmro_id"]
    assert response.json()[0]["txto_protonmail"] == datos_usuario["txto_protonmail"]

# Testing de la funcion /getUser/id
def test_obtener_usuarios_id(db: Session, test_client: TestClient):
    response = test_client.get("/getUser/id/48")
    assert response.status_code == 200
    assert isinstance(response.json(), dict)
    assert len(response.json()) == 6

    assert response.json()["nmro_id"] == datos_usuario["nmro_id"]
    assert response.json()["txto_protonmail"] == datos_usuario["txto_protonmail"]

# Testing de la funcion /getUser/protonmail
def test_obtener_usuarios_protonmail(db: Session, test_client: TestClient):
    response = test_client.get("/getUser/protonmail/usuario1@ejemplo.com")
    assert response.status_code == 200
    assert isinstance(response.json(), dict)
    assert len(response.json()) == 6

    assert response.json()["nmro_id"] == datos_usuario["nmro_id"]
    assert response.json()["txto_protonmail"] == datos_usuario["txto_protonmail"]
    
# Testing de la funcion /setUsers
def test_insertar_usuarios(db: Session, test_client: TestClient):
    response = test_client.post("/setUsers", json=datos_usuario)
    assert response.status_code == 200

    usuario_creado = response.json()
    assert usuario_creado["txto_protonmail"] == datos_usuario["txto_protonmail"]
    assert usuario_creado["txto_nick"] == datos_usuario["txto_nick"]
    assert usuario_creado["cdgo_rango"] == datos_usuario["cdgo_rango"]

    usuario_db = db.query(Model_User).filter_by(txto_protonmail=datos_usuario["txto_protonmail"]).first()
    assert usuario_db is not None
    assert usuario_db.txto_nick == datos_usuario["txto_nick"]
    assert usuario_db.cdgo_rango == datos_usuario["cdgo_rango"]

# Testing de la funcion /changeUser/name
def test_modificar_nombre_usuario(db: Session, test_client: TestClient):
    datos_modificacion = {
        "txto_nick": "usuario1"
    }

    response = test_client.put(f"/changeUser/name/48", json=datos_modificacion)
    assert response.status_code == 200

    usuario_modificado = response.json()
    assert usuario_modificado["nmro_id"] == datos_usuario["nmro_id"]
    assert usuario_modificado["txto_nick"] == datos_modificacion["txto_nick"]

    usuario_db = db.query(Model_User).get(datos_usuario["nmro_id"])
    assert usuario_db.txto_nick == datos_modificacion["txto_nick"]

# Testing de la funcion /changeUser/foto
def test_modificar_foto_usuario(db: Session, test_client: TestClient):
    datos_modificacion = {
        "foto_perfil": "ruta/imagen1.jpg"
    }

    response = test_client.put(f"/changeUser/foto/48", json=datos_modificacion)
    assert response.status_code == 200

    usuario_modificado = response.json()
    assert usuario_modificado["nmro_id"] == datos_usuario["nmro_id"]
    assert usuario_modificado["foto_perfil"] == datos_modificacion["foto_perfil"]

    usuario_db = db.query(Model_User).get(datos_usuario["nmro_id"])
    assert usuario_db.foto_perfil == datos_modificacion["foto_perfil"]

# Testing de la funcion /changeUser/rango
def test_modificar_rango_usuario(db: Session, test_client: TestClient):
    response = test_client.put(f"/changeUser/rango/48")

    assert response.status_code == 200

    usuario_modificado = response.json()
    assert usuario_modificado["nmro_id"] == datos_usuario["nmro_id"]
    assert usuario_modificado["cdgo_rango"] == datos_usuario["cdgo_rango"] + 1

    usuario_db = db.query(Model_User).get(datos_usuario["nmro_id"])
    assert usuario_db.cdgo_rango == datos_usuario["cdgo_rango"] + 1

# Testing de la funcion /delUsers/id
def test_borrar_usuario(db: Session, test_client: TestClient):
    response = test_client.delete(f"/delUsers/59")

    assert response.status_code == 200

    mensaje_salida = response.json()
    assert mensaje_salida["mensaje"] == 0

    usuario_eliminado = db.query(Model_User).get(59)
    assert usuario_eliminado is None