FROM node:latest
WORKDIR /frontend
RUN npm install -g @angular/cli@latest
COPY package.json package-lock.json ./
RUN npm ci
COPY . .
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0", "--disable-host-check"]