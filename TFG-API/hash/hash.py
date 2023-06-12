import hashlib
import os

def get_sha256_hash(password):
    try:
        digest = hashlib.sha256()
        digest.update(password.encode('utf-8'))
        hashed_bytes = digest.digest()
        return bytes_to_hex(hashed_bytes)
    except Exception as e:
        print(e)
        return None
    
def bytes_to_hex(bytes):
    return ''.join(format(b, '02x') for b in bytes)

password1 = "contraseña1"
password2 = "contraseña2"

print(get_sha256_hash(password1))
print(get_sha256_hash(password2))