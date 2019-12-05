class AbstractModel:
    def save(self, commit=True):
        try:
            db.session.add(self)
            if commit:
                db.session.commit()
        except Exception:
            db.session.rollback()

    def delete(self, commit=True):
        try:
            db.session.delete(self)
            if commit:
                db.session.commit()
        except:
            db.session.rollback()

    def update(self):
        pass

    def to_representation(self):
        return {}