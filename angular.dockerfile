# Stage 0: compile angular frontend
FROM node:latest as build
WORKDIR /app
COPY angular/ . 
RUN npm install
RUN npm run build --prod --aot

####Stage 1, Build Nginx backend
FROM nginx:latest
COPY --from=build /app/dist/angular /usr/share/nginx/html
COPY --from=build /app/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80